import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.favorites.FavoritesInterface;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FlickrAPITest {
    private final String apiKey = "9a4794f8dcebfc24e6050c909fbb6ba0";
    private final String sharedSecret = "778afac6bc65bcf1";
    private final String UserId = "161473432@N05";

    /*@Test
    public void searchFavorite() throws FlickrException {
        Flickr f = new Flickr(apiKey, sharedSecret, new REST());
        FavoritesInterface favoritesInterface = f.getFavoritesInterface();

        String extraDataTab[] = {"description", "owner_name"};
        Set<String> extra = new HashSet<String>(Arrays.asList(extraDataTab));

        PhotoList<Photo> favoritePhotoList = favoritesInterface.getList(UserId,5,0, extra);
        Assert.assertFalse(favoritePhotoList.isEmpty());

        favoritePhotoList.forEach(p ->
        {

            System.out.println(String.format("Title: %s", p.getTitle()));
            System.out.println(String.format("ID Photo : %s", p.getId()));

            System.out.println(String.format("Owner : %s", p.getOwner().getUsername()));
            System.out.println(String.format("Description Photo : %s", p.getDescription()));
            System.out.println(String.format("Large Photo URL: %s", p.getLargeUrl()));
            System.out.println(String.format("Small Photo URL: %s\n", p.getSmallUrl()));
        });
    }*/
    /*
    @Test
    public void test() {

    }*/
}
