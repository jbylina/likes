package pw.rapit.likes.config;

import de.flapdoodle.embed.process.config.IRuntimeConfig;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class LocalMongoConfiguration extends EmbeddedMongoAutoConfiguration {

    public LocalMongoConfiguration(MongoProperties properties, EmbeddedMongoProperties embeddedProperties, ApplicationContext context, IRuntimeConfig runtimeConfig) {
        super(properties, embeddedProperties, context, runtimeConfig);
    }
}
