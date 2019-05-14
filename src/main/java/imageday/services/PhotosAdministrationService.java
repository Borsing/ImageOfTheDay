package imageday.services;

import com.flickr4java.flickr.FlickrException;
import imageday.repositories.PhotosFlickrRepository;
import imageday.entities.PhotoPublication;
import imageday.exceptions.PhotoPublicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class PhotosAdministrationService {

    private final PhotosFlickrRepository photosFlickrRepository ;

    @Autowired
    PhotosAdministrationService(PhotosFlickrRepository photosFlickrRepository ){
        this.photosFlickrRepository = photosFlickrRepository;
    }

    /**
     *
     * @param newPhotoPublication
     * @return
     */
    public PhotoPublication addPhotoFlickr(PhotoPublication newPhotoPublication){
        return this.photosFlickrRepository.save(newPhotoPublication);
    }

    /**
     *
     * @param updatedPhotoPublication
     * @param day
     * @return
     * @throws PhotoPublicationException
     */
    public PhotoPublication updatePhoto(PhotoPublication updatedPhotoPublication, Date day) throws PhotoPublicationException {
        return this.photosFlickrRepository.findById(day)
                .map(photoFlickr -> {
                    photoFlickr.setTitle(updatedPhotoPublication.getTitle());
                    photoFlickr.setDayPublication(updatedPhotoPublication.getDayPublication());
                    photoFlickr.setFlickrId(updatedPhotoPublication.getFlickrId());
                    photoFlickr.setDescription(updatedPhotoPublication.getDescription());
                    photoFlickr.setUrlFlickrSmall(updatedPhotoPublication.getUrlFlickrSmall());
                    photoFlickr.setUrlFlickrLarge(updatedPhotoPublication.getUrlFlickrLarge());
                    return this.photosFlickrRepository.save(photoFlickr);
                }).orElseThrow(() -> new PhotoPublicationException("Any photoFlickr exists with the id : " + day));
    }

    /**
     *
     * @param day
     */
    public void deletePhoto(Date day){
       this.photosFlickrRepository.deleteById(day);
    }


    /**
     *
     * @return
     * @throws PhotoPublicationException
     * @throws FlickrException
     */
    public PhotoPublication addPhotoOfToday(List<PhotoPublication> photosToSelect) throws PhotoPublicationException, FlickrException {
        List<PhotoPublication> newsPhotos = this.removePreviousPhoto(photosToSelect,this.getPreviousFlickrIds());

        if(newsPhotos.isEmpty())
            throw new PhotoPublicationException("All photos selected are all ready choosed for previous days");

        //pickOneRandom
        Random rand = new Random();
        PhotoPublication photoOfToday = newsPhotos.get(rand.nextInt(newsPhotos.size()));

        //addInDb
        return this.photosFlickrRepository.save(photoOfToday);
    }

    public boolean photoForTheDayIsChoosed(Date day){
        return this.photosFlickrRepository.findByDayPublication(day) != null ;
    }

    /**
     *
     * @param photosToSelect
     * @param previousPhotosIds
     * @return
     */
    private List<PhotoPublication> removePreviousPhoto(List<PhotoPublication> photosToSelect, List<String> previousPhotosIds) {
        return photosToSelect
                .stream()
                .filter(photo ->
                        photo != null &&
                        !previousPhotosIds.contains(photo.getFlickrId())) //remove previous photo all ready selected for another day
                .collect(Collectors.toList());
    }

    /**
     *
     * @return
     */
    private List<String> getPreviousFlickrIds(){
        List<String> previousFlickrIds = new ArrayList<>();
        this.photosFlickrRepository
                .findAll()
                .forEach(previousPhoto ->
                        previousFlickrIds.add(previousPhoto.getFlickrId())
                );
        return previousFlickrIds;
    }
}
