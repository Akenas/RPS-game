package mad.com.rpsmanager.infrastructure.controller.websockets;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameWebsocketHandler implements WebSocketHandler {

    private final GameEventWebSocketProcessor eventWebSocketProcessor;
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
        eventWebSocketProcessor.addSession(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        
        if (message instanceof TextMessage) {
            eventWebSocketProcessor.transformAndAccept((TextMessage) message);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Handle any errors
        System.err.println("Transport error: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        eventWebSocketProcessor.removeSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}