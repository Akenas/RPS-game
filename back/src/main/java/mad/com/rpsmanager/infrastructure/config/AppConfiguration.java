package mad.com.rpsmanager.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class for the Rock Paper Scissors Game Manager application.
 * <p>
 * This class defines the beans and application-level configurations used throughout the application.
 * </p>
 */
@Configuration
@Import({GameServiceConfiguration.class,JpaPersistanceConfiguration.class, AuthenticationConfig.class,SecurityConfig.class, WebSocketConfiguration.class})
public class AppConfiguration {
    

}
