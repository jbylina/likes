package pw.rapit.likes.config;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private static final Version FB_API_VERSION = Version.VERSION_2_8;

    @Bean
    public FacebookClient.AccessToken accessToken(@Value("${restfb.appId}") String appId,
                                                  @Value("${restfb.appSecret}") String appSecret) {
        return new DefaultFacebookClient(FB_API_VERSION)
                .obtainAppAccessToken(appId, appSecret);
    }

    @Bean
    public FacebookClient facebookClient(FacebookClient.AccessToken token) {
        return new DefaultFacebookClient(token.getAccessToken(), FB_API_VERSION);
    }
}
