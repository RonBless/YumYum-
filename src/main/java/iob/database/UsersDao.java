package iob.database;

import org.springframework.data.repository.PagingAndSortingRepository;

import iob.data.UserEntity;

public interface UsersDao extends PagingAndSortingRepository<UserEntity, String> {

}
