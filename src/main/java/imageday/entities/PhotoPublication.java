package imageday.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class PhotoPublication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private Date dayPublication;

    @NotBlank
    @NotNull
    private String flickrId ;

    @NotNull
    private String ownerName ;

    private String title ;

    @Column(columnDefinition="text")
    private String description ;

    @NotNull
    @NotBlank
    private String urlFlickrLarge ;

    @NotNull
    @NotBlank
    private String urlFlickrSmall ;

    /**
     * Default constructor for Spring usage
     */
    public PhotoPublication(){

    }

    /**
     *
     * @param dayPublication
     * @param flickrId
     * @param title
     * @param description
     * @param ownerName
     * @param urlFlickrLarge
     * @param urlFlickrSmall
     */
    public PhotoPublication(Date dayPublication, String flickrId, String title, String description, String ownerName, String urlFlickrLarge, String urlFlickrSmall){
        this.dayPublication = dayPublication;
        this.flickrId = flickrId ;
        this.title = title ;
        this.description = description ;
        this.ownerName = ownerName ;
        this.urlFlickrLarge = urlFlickrLarge ;
        this.urlFlickrSmall = urlFlickrSmall ;

    }
}