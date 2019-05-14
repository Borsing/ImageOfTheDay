package imageday.services;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.favorites.FavoritesInterface;
import com.flickr4java.flickr.photos.Photo;
import imageday.configuration.ConfigurationService;
import imageday.entities.PhotoPublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlickrService {

    private final ConfigurationService configurationService ;

    private final FavoritesInterface favoritesInterface ;

    @Autowired
    FlickrService(ConfigurationService configurationService){
        this.configurationService = configurationService ;
        this.favoritesInterface  = new FavoritesInterface(configurationService.getFlickrApiKey(), configurationService.getFlickrSharedSecret(), new REST());
    }

    private Set<String> getExtraData(){
        String extraDataTab[] = {"description", "owner_name"};
        return new HashSet<String> (Arrays.asList(extraDataTab));
    }

    public PhotoPublication convertPhoto2PhotoFlickr(Photo photo, Date date){
        if (photo.getId() == null ||
                photo.getOwner() == null ||
                photo.getOwner().getUsername() == null ||
                photo.getLargeUrl() == null ||
                photo.getSmallUrl() == null) return null;

        return new PhotoPublication(
                date,
                photo.getId(),
                photo.getTitle(),
                photo.getDescription(),
                photo.getOwner().getUsername(),
                photo.getLargeUrl(),
                photo.getSmallUrl());
    }

    public List<PhotoPublication> convertPhotos2PhotosFlickr(List<Photo> photos, Date date){
        return photos.stream()
                .map(photo -> this.convertPhoto2PhotoFlickr(photo, date)) //convert photo from Flickr Library to our model
                .collect(Collectors.toList());
    }

    public List<Photo> getAllFavorites() throws FlickrException {
        Set<String> extraData = this.getExtraData();

        List<Photo> allfavorites = new ArrayList<>();

        for(int page = 0; allfavorites.size() == page*configurationService.getFlickrMaxPerPage(); page++) {
            allfavorites.addAll( this.favoritesInterface.getList(configurationService.getFlickrUserId(), configurationService.getFlickrMaxPerPage(), page, extraData));
        }

        return allfavorites ;
    }



}
