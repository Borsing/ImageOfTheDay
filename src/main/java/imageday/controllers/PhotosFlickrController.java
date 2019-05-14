package imageday.controllers;


import imageday.entities.PhotoPublication;
import imageday.exceptions.PhotoPublicationException;
import imageday.services.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/photos")
public class PhotosFlickrController {

    private final PhotosService photosService;

    @Autowired
    PhotosFlickrController(PhotosService photosService){
        this.photosService = photosService ;
    }

    @GetMapping("/")
    public Iterable<PhotoPublication> findAll(){
        return photosService.getAllPhotos();
    }

    @GetMapping("/{day}")
    public PhotoPublication findPhoto(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")  Date day) throws PhotoPublicationException {
        return photosService.findPhotoByDay(day);
    }

    @GetMapping("/photooftheday")
    public PhotoPublication findPhotoOfTheDay(){
        return photosService.findPhotoOfTheDay();
    }

    @GetMapping("/previousphotooftheday")
    public List<PhotoPublication> findPreviousPhotoOfTheDay(){
        return photosService.findPreviousPhotosOfTheDay();
    }
}
