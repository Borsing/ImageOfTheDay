package imageday.repositories;

import java.util.Date;
import java.util.List;

import imageday.entities.PhotoPublication;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosFlickrRepository extends PagingAndSortingRepository<PhotoPublication, Date> {

    PhotoPublication findByDayPublication(@Param("dayPublication")Date photoOfTheDay);

    List<PhotoPublication> findByDayPublicationBefore(@Param("dayPublication")Date photoOfTheyDay);

}