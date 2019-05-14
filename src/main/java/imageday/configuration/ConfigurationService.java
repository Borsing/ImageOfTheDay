package imageday.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ConfigurationService {

    @Value("${flickr.apiKey}")
    private String flickrApiKey ;

    @Value("${flickr.sharedSecret}")
    private String flickrSharedSecret;

    @Value("${flickr.userId}")
    private String flickrUserId;

    @Value("${flickr.maxPerPage}")
    private int flickrMaxPerPage;

    @Value("${admin.name}")
    private String adminUsername ;

    @Value("${admin.password}")
    private String adminPassword ;
}
