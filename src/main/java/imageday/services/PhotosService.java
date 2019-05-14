package imageday.services;

import imageday.exceptions.PhotoPublicationException;
import imageday.repositories.PhotosFlickrRepository;
import imageday.entities.PhotoPublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PhotosService {

    private final PhotosFlickrRepository photosFlickrRepository ;

    @Autowired
    PhotosService(PhotosFlickrRepository photosFlickrRepository){
        this.photosFlickrRepository = photosFlickrRepository ;
    }

    public Iterable<PhotoPublication> getAllPhotos(){
        return photosFlickrRepository.findAll();
    }

    public PhotoPublication findPhotoOfTheDay(){
        return photosFlickrRepository.findByDayPublication(new Date());
    }

    public PhotoPublication findPhotoByDay(Date day) throws PhotoPublicationException {
        return photosFlickrRepository.findById(day).orElseThrow(() -> new PhotoPublicationException("No image for the day : " + day));
    }

    public List<PhotoPublication> findPreviousPhotosOfTheDay(){
        return photosFlickrRepository.findByDayPublicationBefore(new Date());
    }
}
