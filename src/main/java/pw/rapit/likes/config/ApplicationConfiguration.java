package pw.rapit.likes.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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

    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findModulesViaServiceLoader(true)
                .modulesToInstall(new ParameterNamesModule(), new Jdk8Module(), new JavaTimeModule());
    }
}
