package imageday.controllers;

import com.flickr4java.flickr.FlickrException;
import imageday.entities.PhotoPublication;
import imageday.exceptions.PhotoPublicationException;
import imageday.services.FlickrService;
import imageday.services.PhotosAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/photos/admin")
public class PhotosFlickrAdministrationController {

    @Autowired
    PhotosAdministrationService photosAdministrationService;

    @Autowired
    FlickrService flickrService;

    @PostMapping(value = "/", consumes = "application/json")
    public PhotoPublication addPhotoFlickr(@RequestBody PhotoPublication newPhotoPublication){
        return this.photosAdministrationService.addPhotoFlickr(newPhotoPublication);
    }

    @PostMapping(value = "/addphotooftheday")
    public PhotoPublication addPhotoOfToday() throws FlickrException, PhotoPublicationException {
        Date today = new Date();
        if(this.photosAdministrationService.photoForTheDayIsChoosed(today))
            throw new PhotoPublicationException("Photo of the day is all ready set today");

        return  this.photosAdministrationService.addPhotoOfToday(
                    this.flickrService.convertPhotos2PhotosFlickr(
                            this.flickrService.getAllFavorites(), today));
    }


    @PutMapping("/{day}")
    public PhotoPublication updatePhotoFlickr(@RequestBody PhotoPublication updatedPhotoPublication, @PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy") Date day) throws PhotoPublicationException {
        return this.photosAdministrationService.updatePhoto(updatedPhotoPublication,day);
    }

    @DeleteMapping("/{day}")
    public void deletePhotoFlickr(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy") Date day) {
        this.photosAdministrationService.deletePhoto(day);
    }

}
