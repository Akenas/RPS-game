package mad.com.rpsmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mad.com.rpsmanager.controller.AppController;

/**
 * Configuration class for the Rock Paper Scissors Game Manager application.
 * <p>
 * This class defines the beans and application-level configurations used throughout the application.
 * </p>
 */
@Configuration
public class AppConfiguration {
    
    /**
     * Creates and returns an instance of {@link AppController}.
     *
     * @return a new {@link AppController} instance
     */
    @Bean
    public AppController appController(){
        return new AppController();
    }
}
