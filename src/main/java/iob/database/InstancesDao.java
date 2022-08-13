package iob.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import iob.data.InstanceEntity;

public interface InstancesDao extends PagingAndSortingRepository<InstanceEntity, String> {
	public List<InstanceEntity> findAllByName(@Param("name") String name, Pageable pageable);
	public List<InstanceEntity> findAllByNameAndActive(@Param("name") String name,	@Param("active") boolean active, Pageable pageable);
	public List<InstanceEntity> findAllByType(@Param("type") String type, Pageable pageable);
	public List<InstanceEntity> findAllByTypeAndActive(@Param("type") String type,@Param("active") boolean active, Pageable pageable);
	public List<InstanceEntity> findAllByLatBetweenAndLngBetween(@Param("minLat") double minLat,@Param("maxLat") double maxLat, @Param("minLng") double minLng, @Param("maxLng") double maxLng, Pageable pageable);
	public List<InstanceEntity> findAllByLatAndLngAndActive(@Param("lat") double lat, @Param("lng") double lng,@Param("active") boolean active, Pageable pageable);
	public List<InstanceEntity> findAllByActive(@Param("active") boolean active, Pageable pageable);
	public Optional<InstanceEntity> findBydomainIdAndActive(@Param("domainId") String id, @Param("active") boolean active);

}
