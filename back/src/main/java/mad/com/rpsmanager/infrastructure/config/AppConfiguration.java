package mad.com.rpsmanager.infrastructure.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import mad.com.rpsmanager.domain.repositories.GameMatchRepository;
import mad.com.rpsmanager.domain.repositories.GameModeRepository;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;
import mad.com.rpsmanager.infrastructure.controller.GameController;
import mad.com.rpsmanager.service.game.GameService;
import mad.com.rpsmanager.service.game.InMemoryGameService;
import mad.com.rpsmanager.service.game.PersistentGameService;

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
