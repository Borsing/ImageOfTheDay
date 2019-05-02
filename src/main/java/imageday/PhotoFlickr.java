package imageday;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PhotoFlickr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String flickrId ;

    private String title ;

    private String description ;

    private String urlFlickrLarge ;

    private String urlFlickrSmall ;

    public String getFlickrId() {
        return flickrId;
    }

    public void setFlickrId(String flickrId) {
        this.flickrId = flickrId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlFlickrLarge() {
        return urlFlickrLarge;
    }

    public void setUrlFlickrLarge(String urlFlickrLarge) {
        this.urlFlickrLarge = urlFlickrLarge;
    }

    public String getUrlFlickrSmall() {
        return urlFlickrSmall;
    }

    public void setUrlFlickrSmall(String urlFlickrSmall) {
        this.urlFlickrSmall = urlFlickrSmall;
    }
}