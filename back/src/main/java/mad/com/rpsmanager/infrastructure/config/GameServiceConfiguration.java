package mad.com.rpsmanager.infrastructure.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mad.com.rpsmanager.domain.repositories.GameMatchRepository;
import mad.com.rpsmanager.domain.repositories.GameModeRepository;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;
import mad.com.rpsmanager.infrastructure.controller.GameController;
import mad.com.rpsmanager.service.game.GameService;
import mad.com.rpsmanager.service.game.InMemoryGameService;
import mad.com.rpsmanager.service.game.PersistentGameService;

@Configuration
public class GameServiceConfiguration {
    
    @Bean
    public GameController gameController(GameService service){
        return new GameController(service);
    }

    @Bean
    @ConditionalOnProperty(name = "com.mad.rpsmanager.persistence.enabled", havingValue = "false", matchIfMissing = true)
    public GameService inMemoryGameService(){
        GameService service = new InMemoryGameService();
        service.init();
        return service;
    }

    @Bean
    @ConditionalOnProperty(name = "com.mad.rpsmanager.persistence.enabled", havingValue = "true", matchIfMissing = false)
    public GameService persistentGameService(GameModeRepository gameModeRepository, PlayerRepository playerRepository, GameMatchRepository repository){
        GameService service = new PersistentGameService(gameModeRepository, playerRepository, repository);
        service.init();
        return service;
    }
}
