package imageday;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "photosflickr", path = "photosflickr")
public interface PhotosFlickrRepository extends PagingAndSortingRepository<PhotoFlickr, Long> {

    List<PhotoFlickr> findByTitle(@Param("title") String title);

}