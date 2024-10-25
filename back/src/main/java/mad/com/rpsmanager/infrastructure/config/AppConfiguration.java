package mad.com.rpsmanager.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import mad.com.rpsmanager.infrastructure.controller.GameController;
import mad.com.rpsmanager.service.game.GameService;

/**
 * Configuration class for the Rock Paper Scissors Game Manager application.
 * <p>
 * This class defines the beans and application-level configurations used throughout the application.
 * </p>
 */
@Configuration
@Import(SecurityConfig.class)
public class AppConfiguration {
    
    /**
     * Creates and returns an instance of {@link GameController}.
     *
     * @return a new {@link GameController} instance
     */
    @Bean
    public GameController appController(GameService service){
        return new GameController(service);
    }

    @Bean
    public GameService gameService(){
        return new GameService();
    }
}
