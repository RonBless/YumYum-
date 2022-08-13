package iob.logic;

import java.util.List;

import iob.boundary.UserBoundary;

public interface EnchancedUserService extends UsersService {
	public List<UserBoundary> getAllUsers(String userDomain, String userEmail, int size, int page);
	public void deleteAllUsers(String userDomain, String userEmail);
}
