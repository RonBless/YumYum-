package iob.database;

import org.springframework.data.repository.PagingAndSortingRepository;

import iob.data.ActivityEntity;


public interface ActivtiesDao extends PagingAndSortingRepository<ActivityEntity, String> {

}
