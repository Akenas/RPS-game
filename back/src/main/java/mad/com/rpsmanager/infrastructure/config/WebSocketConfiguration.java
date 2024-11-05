package mad.com.rpsmanager.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;

import mad.com.rpsmanager.infrastructure.controller.websockets.GameEventWebSocketProcessor;
import mad.com.rpsmanager.infrastructure.controller.websockets.GameWebsocketHandler;
import mad.com.rpsmanager.service.game.GameService;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GameService gameService;
   
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
            .addHandler(new GameWebsocketHandler(gameEventWebSocketProcessor(gameService,mapper)), "/game")
            .setAllowedOrigins("*");
    }

    private GameEventWebSocketProcessor gameEventWebSocketProcessor(GameService gameService,ObjectMapper mapper){
        return new GameEventWebSocketProcessor(gameService,mapper);
    }
}