package iob.logic;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iob.boundary.UserBoundary;
import iob.data.UserEntity;
import iob.data.UserRole;
import iob.database.UsersDao;
import iob.util.AccessDeniedException;
import iob.util.NotFoundException;
import iob.util.UnprocessableEntity;
import iob.util.UserConverter;

@Service
public class UsersServiceImplementation implements EnchancedUserService {
	private UserConverter userConverter;
	private String domain;
	private String splitChar;
	private UsersDao usersDao;

	@Value("${entity.split.character}")
	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}

	@Value("${spring.application.name}")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Autowired
	public UsersServiceImplementation(UserConverter converter, UsersDao dao) {
		userConverter = converter;
		usersDao = dao;
	}

	@Override
	@Transactional
	public UserBoundary createUser(UserBoundary user) {
		if(user.getUserId() != null) {
			String userEmail = user.getUserId().getEmail();
			if(  userEmail != null && !userEmail.trim().isEmpty()) {
			    String regexPattern = "^(.+)@(\\S+)$";
			    if(!patternMatches(userEmail, regexPattern))
			    	throw new UnprocessableEntity("Invalid Email Address");
			}
			else 
				throw new UnprocessableEntity("Email Address Can't be empty");
		}
		
		try
		{
			login(user.getUserId().getDomain(),user.getUserId().getEmail());
			throw new UnprocessableEntity("User already exists!");
		}
		catch(NotFoundException e)
		{
		}
		
		String userAvatar = user.getAvatar();
		if(userAvatar == null || userAvatar.trim().isEmpty()) {
			throw new UnprocessableEntity("Avatar can't be null");
		}

		UserEntity result = userConverter.toEntity(user);
		result.setDomainEmail(domain + splitChar + result.getDomainEmail().split(splitChar)[1]);
		if(result.getUsername() == null || result.getUsername().isEmpty())
		{
			throw new UnprocessableEntity("Username cannot be empty!");
		}
		usersDao.save(result);
		return userConverter.toBoundary(result);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary login(String userDomain, String userEmail) {
		return userConverter.toBoundary(usersDao.findById(userDomain + splitChar + userEmail).orElseThrow(() -> new NotFoundException(
				"Could not find user with domain and email input: " + userDomain + userEmail)));
	}

	@Override
	@Transactional
	public UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update) {
		UserEntity myUser = usersDao.findById(userDomain + splitChar + userEmail).orElseThrow(() -> new NotFoundException(
				"Could not find user with domain and email input: " + userDomain + userEmail));
		if (update.getRole() != null) {
			try {
				myUser.setRole(UserRole.valueOf(update.getRole()));
			} catch (IllegalArgumentException e) {
			}
		}
		if (update.getUsername() != null && !update.getUsername().trim().isEmpty())
			myUser.setUsername(update.getUsername());
		if (update.getAvatar() != null && !update.getAvatar().trim().isEmpty())
			myUser.setAvatar(update.getAvatar());
		return userConverter.toBoundary(myUser);
	}

	@Override
	@Deprecated
	public List<UserBoundary> getAllUsers() {
		throw new RuntimeException("deprecated method");
	}

	@Override
	@Deprecated
	public void deleteAllUsers() {
		throw new RuntimeException("deprecated method");
	}
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String userDomain, String userEmail, int size, int page) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail).orElseThrow(()->new NotFoundException("User not found"));
		if(user.getRole()== UserRole.ADMIN)
		{
			return usersDao.findAll(PageRequest.of(page, size, Direction.ASC, "avatar")).stream().map(userConverter::toBoundary).collect(Collectors.toList());
		}
		throw new AccessDeniedException("premission denied for role");
	}

	@Override
	@Transactional
	public void deleteAllUsers(String userDomain, String userEmail) {
		UserEntity user = usersDao.findById(userDomain + splitChar + userEmail).orElseThrow(()->new NotFoundException("User not found"));
		if(user.getRole()== UserRole.ADMIN)
		{
			usersDao.deleteAll();
		}
		else
		{
			throw new AccessDeniedException("premission denied for role");
		}
	}
	
}
