package imageday.services;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.favorites.FavoritesInterface;
import com.flickr4java.flickr.photos.Photo;
import imageday.entities.PhotoPublication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlickrService {

    private final String apiKey = "9a4794f8dcebfc24e6050c909fbb6ba0";
    private final String sharedSecret = "778afac6bc65bcf1";
    private final String userId = "161473432@N05";
    private final int maxPerPage = 500 ; //from Flickr API

    private FavoritesInterface favoritesInterface = new FavoritesInterface(apiKey,sharedSecret, new REST());

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

        for(int page = 0; allfavorites.size() == page*maxPerPage; page++) {
            allfavorites.addAll( this.favoritesInterface.getList(userId, maxPerPage, page, extraData));
        }

        return allfavorites ;
    }



}
