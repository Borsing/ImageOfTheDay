package imageday.services;

import imageday.repositories.PhotosFlickrRepository;
import imageday.entities.PhotoPublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PhotosService {

    @Autowired
    PhotosFlickrRepository photosFlickrRepository ;

    public Iterable<PhotoPublication> getAllPhotos(){
        return photosFlickrRepository.findAll();
    }

    public PhotoPublication findPhotoOfTheDay(){
        return photosFlickrRepository.findByDayPublication(new Date());
    }

    public List<PhotoPublication> findPreviousPhotosOfTheDay(){
        return photosFlickrRepository.findByDayPublicationBefore(new Date());
    }
}
