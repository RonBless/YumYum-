package iob.logic;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iob.boundary.InstanceBoundary;
import iob.boundary.LocationBoundary;
import iob.boundary.UserIDBoundary;
import iob.data.InstanceEntity;
import iob.data.UserEntity;
import iob.data.UserRole;
import iob.database.InstancesDao;
import iob.database.UsersDao;
import iob.util.AccessDeniedException;
import iob.util.InstanceConverter;
import iob.util.NotFoundException;
import iob.util.UnprocessableEntity;

@Service
public class InstanceServiceImplementation implements EnchancedInstanceService {
	private InstanceConverter converter;
	private InstancesDao instanceDao;
	private UsersDao usersDao;
	private String domain;
	private String splitChar;

	@Value("${entity.split.character}")
	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}
	@Value("${spring.application.name}")
	public void setDomain(String domain) {
		this.domain = domain;
	}
	@Autowired
	public InstanceServiceImplementation(InstanceConverter converter, InstancesDao dao, UsersDao usersdao) {
		this.converter = converter;
		instanceDao = dao;
		usersDao = usersdao;
	}

	@Override
	@Deprecated
	public InstanceBoundary updateInstance(String instanceDomain, String instanceID, InstanceBoundary update) {
		throw new RuntimeException("deprecated method");
	}
	@Override
	@Deprecated
	public InstanceBoundary getSpecificInstance(String instanceDomain, String instanceID) {
		throw new RuntimeException("deprecated method");
	}
	@Override
	@Deprecated
	public List<InstanceBoundary> getAllInstances() {
		throw new RuntimeException("deprecated method");
	}

	@Override
	@Deprecated
	public void deleteAllInstances() {
		throw new RuntimeException("deprecated method");
	}

	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllInstancesBySpecificName(String name, int size, int page, String userEmail,
			String userDomain) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail)
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.MANAGER) {
			return instanceDao.findAllByName(name, PageRequest.of(page, size, Direction.ASC, "domainId")).stream()
					.map(converter::toBoundary).collect(Collectors.toList());
		}
		if (user.getRole() == UserRole.PLAYER)
		{
			return instanceDao.findAllByNameAndActive(name,true, PageRequest.of(page, size, Direction.ASC, "domainId")).stream()
					.map(converter::toBoundary).collect(Collectors.toList());
		}
		throw new AccessDeniedException("premission denied for role");
	}
	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllInstancesBySpecificType(String type, int size, int page, String userEmail,
			String userDomain) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail)
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.MANAGER) {
			return instanceDao.findAllByType(type, PageRequest.of(page, size, Direction.ASC, "domainId")).stream()
					.map(converter::toBoundary).collect(Collectors.toList());
		}
		if (user.getRole() == UserRole.PLAYER)
		{
			return instanceDao.findAllByTypeAndActive(type,true, PageRequest.of(page, size, Direction.ASC, "domainId")).stream()
					.map(converter::toBoundary).collect(Collectors.toList());
		}
		throw new AccessDeniedException("premission denied for role");
	}
	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllInstancesNear(LocationBoundary location, int size, int page, String userEmail,
			String userDomain, double distance) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail)
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.MANAGER) {
			return instanceDao.findAllByLatBetweenAndLngBetween(location.getLat()-distance,location.getLat()+distance,location.getLng()-distance,location.getLng()+distance, PageRequest.of(page, size, Direction.ASC, "domainId")).stream()
					.map(converter::toBoundary).collect(Collectors.toList());
		}
		if (user.getRole() == UserRole.PLAYER)
		{
			return instanceDao.findAllByLatAndLngAndActive(location.getLat(),location.getLng(),true, PageRequest.of(page, size, Direction.ASC, "domainId")).stream()
					.map(converter::toBoundary).collect(Collectors.toList());
		}
		throw new AccessDeniedException("premission denied for role");
	}
	@Override
	@Transactional
	public void deleteAllInstances(String userDomain, String userEmail) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail)
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.ADMIN) {
			instanceDao.deleteAll();
		}
		else
		{
			throw new AccessDeniedException("premission denied for role");
		}
	}
	@Override
	@Transactional
	public InstanceBoundary createInstance(InstanceBoundary boundary) {
		if(boundary.getCreatedBy() == null || boundary.getCreatedBy().getUserId() == null || boundary.getCreatedBy().getUserId().getDomain() == null || boundary.getCreatedBy().getUserId().getEmail() == null)
			throw new UnprocessableEntity("Instance boundary is ill formated, missing created by info!");
		UserIDBoundary idBoundary = boundary.getCreatedBy().getUserId();
		UserEntity user = usersDao.findById(idBoundary.getDomain() + splitChar + idBoundary.getEmail())
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.MANAGER) {
			InstanceEntity instanceEntity = converter.toEntity(boundary);
			instanceEntity.setDomainId(domain + splitChar + UUID.randomUUID().toString());
			instanceEntity.setCreatedTimestamp(Date.from(Instant.now()));
			if (instanceEntity.getName() == null || instanceEntity.getName().trim().isEmpty()
					|| instanceEntity.getType() == null || instanceEntity.getType().trim().isEmpty()) {
				throw new UnprocessableEntity("Entity type or name is not written!");
			}
			instanceEntity = instanceDao.save(instanceEntity);
			return converter.toBoundary(instanceEntity);
		}
		throw new AccessDeniedException("premission denied for role");
	}
	@Override
	@Transactional
	public InstanceBoundary updateInstance(String instanceDomain, String instanceID, InstanceBoundary update,
			String userDomain, String userEmail) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail)
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.MANAGER) {
			InstanceEntity instanceForUpdate = instanceDao.findById(instanceDomain + splitChar + instanceID)
					.orElseThrow(() -> new NotFoundException(
							"Could not find instance by domain and id: " + instanceDomain + instanceID));

			if (update.getActive() != null)
				instanceForUpdate.setActive(update.getActive());

			if (update.getInstanceAttributes() != null) {
				instanceForUpdate.setInstanceAttributes(update.getInstanceAttributes());
			}

			if (update.getLocation() != null) {
				instanceForUpdate.setLat(update.getLocation().getLat());
				instanceForUpdate.setLng(update.getLocation().getLng());
			}

			if (update.getName() != null && !update.getName().trim().isEmpty())
				instanceForUpdate.setName(update.getName());

			if (update.getType() != null && !update.getType().trim().isEmpty())
				instanceForUpdate.setType(update.getType());

			instanceForUpdate = instanceDao.save(instanceForUpdate);

			return converter.toBoundary(instanceForUpdate);
		}
		throw new AccessDeniedException("premission denied for role");
	}

	@Override
	@Transactional(readOnly = true)
	public InstanceBoundary getSpecificInstance(String instanceDomain, String instanceID, String userDomain,
			String userEmail) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail)
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.MANAGER) {
			return converter.toBoundary(
					instanceDao.findById(instanceDomain + splitChar + instanceID).orElseThrow(() -> new NotFoundException(
							"Could not find instance by domain and id: " + instanceDomain + instanceID)));
		}
		if (user.getRole() == UserRole.PLAYER)
		{
			return converter.toBoundary(
					instanceDao.findBydomainIdAndActive(instanceDomain + splitChar + instanceID,true).orElseThrow(() -> new NotFoundException(
							"Could not find instance by domain and id: " + instanceDomain + instanceID)));
		}
		throw new AccessDeniedException("premission denied for role");
	}
	@Override
	public List<InstanceBoundary> getAllInstances(int size, int page, String userEmail, String userDomain) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail)
				.orElseThrow(() -> new NotFoundException("User not found"));
		if (user.getRole() == UserRole.MANAGER) {
			return instanceDao.findAll(PageRequest.of(page, size,Direction.ASC,"domainId")).stream().map(converter::toBoundary).collect(Collectors.toList());
		}
		else if(user.getRole() == UserRole.PLAYER)
		{
			return instanceDao.findAllByActive(true,PageRequest.of(page, size,Direction.ASC,"domainId")).stream().map(converter::toBoundary).collect(Collectors.toList());
		}
		throw new AccessDeniedException("premission denied for role");
	}
}
