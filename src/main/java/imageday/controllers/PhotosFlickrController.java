package imageday.controllers;


import imageday.entities.PhotoPublication;
import imageday.services.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/photos")
public class PhotosFlickrController {

    @Autowired
    PhotosService photosService;

    @GetMapping("/")
    public Iterable<PhotoPublication> findAll(){
        return photosService.getAllPhotos();
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
