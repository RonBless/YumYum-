package iob.logic;

import java.util.List;

import iob.boundary.UserBoundary;

public interface UsersService  {
	public UserBoundary createUser(UserBoundary user);
	public UserBoundary login(String userDomain, String userEmail);
	public UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update);
	public List<UserBoundary> getAllUsers();
	public void deleteAllUsers();

}
