package mad.com.rpsmanager.infrastructure.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;
import mad.com.rpsmanager.infrastructure.controller.websockets.GameEventWebSocketProcessor;
import mad.com.rpsmanager.infrastructure.controller.websockets.GameWebsocketHandler;
import mad.com.rpsmanager.service.game.GameService;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final ObjectMapper mapper;
    private final GameService gameService;

    private final Optional<PlayerRepository> playerRepository;
   
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
            .addHandler(new GameWebsocketHandler(gameEventWebSocketProcessor(gameService,playerRepository,mapper)), "/game")
            .setAllowedOrigins("*");
    }

    private GameEventWebSocketProcessor gameEventWebSocketProcessor(GameService gameService,Optional<PlayerRepository> playerRepository, ObjectMapper mapper){
        return new GameEventWebSocketProcessor(gameService,playerRepository,mapper);
    }
}