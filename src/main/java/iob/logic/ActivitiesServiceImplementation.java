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

import iob.boundary.ActivityBoundary;
import iob.boundary.InstanceIDBoundary;
import iob.boundary.UserIDBoundary;
import iob.data.ActivityEntity;
import iob.data.UserEntity;
import iob.data.UserRole;
import iob.database.ActivtiesDao;
import iob.database.InstancesDao;
import iob.database.UsersDao;
import iob.util.AccessDeniedException;
import iob.util.ActivityConverter;
import iob.util.NotFoundException;
import iob.util.UnprocessableEntity;

@Service
public class ActivitiesServiceImplementation implements EnchancedActivitiesService {
	
	private String domain;
	private ActivtiesDao activityDao;
	private UsersDao usersDao;
	private InstancesDao instancesDao;
	private ActivityConverter activityConverter;
	private String splitChar;
	
	@Value("${entity.split.character}")
	public void setSplitChar(String splitChar)
	{
		this.splitChar = splitChar;
	}
	@Value("${spring.application.name}")
	public void setDomain(String domain) {
		this.domain = domain;
	}
	@Autowired
	public ActivitiesServiceImplementation(ActivityConverter converter, ActivtiesDao dao, UsersDao userdao, InstancesDao instancedao)
	{
		activityConverter = converter;
		activityDao = dao;
		usersDao = userdao;
		instancesDao = instancedao;
	}
	@Override
	@Deprecated
	public List<ActivityBoundary> getAllActivities() {
		throw new RuntimeException("deprecated method");
	}
	@Override
	@Deprecated
	public void deleteAllActivities() {
		throw new RuntimeException("deprecated method");
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActivityBoundary> getAllActivities(int page, int size, String userEmail, String userDomain) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail).orElseThrow(()->new NotFoundException("User not found"));
		if(user.getRole()== UserRole.ADMIN)
		{
			return activityDao.findAll(PageRequest.of(page, size, Direction.ASC, "type")).stream().map(activityConverter::toBoundary).collect(Collectors.toList());
		}
		throw new AccessDeniedException("premission denied for role");
	}
	@Override
	@Transactional
	public void deleteAllActivities(String userDomain, String userEmail) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail).orElseThrow(()->new NotFoundException("User not found"));
		if(user.getRole()== UserRole.ADMIN)
		{
			activityDao.deleteAll();
		}
		else
		{
			throw new AccessDeniedException("premission denied for role");
		}
	}
	@Override
	@Transactional
	public Object invokeActivity(ActivityBoundary activity) {
		if(activity.getInvokedBy() == null || activity.getInvokedBy().getUserId() == null || activity.getInvokedBy().getUserId().getDomain() == null || activity.getInvokedBy().getUserId().getEmail() == null)
			throw new UnprocessableEntity("Invoked by is not written fully!");
		UserIDBoundary userIdBoundary = activity.getInvokedBy().getUserId();
		UserEntity user = usersDao.findById(userIdBoundary.getDomain() + splitChar + userIdBoundary.getEmail()).orElseThrow(()->new NotFoundException("User not found"));
		if(user.getRole()== UserRole.PLAYER)
		{
			if(activity.getType() == null || activity.getType().trim().isEmpty()) {
				throw new UnprocessableEntity("Activity Type can't be empty");
			}
			if(activity.getInstance() == null || activity.getInstance().getInstanceId() == null || activity.getInstance().getInstanceId().getId() == null || activity.getInstance().getInstanceId().getDomain() == null || activity.getInstance().getInstanceId().getDomain().trim().isEmpty() || activity.getInstance().getInstanceId().getId().trim().isEmpty())
			{
				throw new UnprocessableEntity("Instance is not written fully!");
			}
			InstanceIDBoundary instanceID = activity.getInstance().getInstanceId();
			instancesDao.findBydomainIdAndActive(instanceID.getDomain() + splitChar + instanceID.getId(), true).orElseThrow(()->new NotFoundException("Instance not found!"));
			ActivityEntity activityEntity = activityConverter.toEntity(activity);
			activityEntity.setCreatedTimestamp(Date.from(Instant.now()));
			activityEntity.setDomainId(domain + splitChar + UUID.randomUUID().toString());
			activityEntity = activityDao.save(activityEntity);
			return activityConverter.toBoundary(activityEntity);
		}
		throw new AccessDeniedException("premission denied for role");
	}
}
