package mad.com.rpsmanager.infrastructure.controller.websockets;

import java.io.IOException;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.transients.events.game.GameMatchPickEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;

@RestController
@RequiredArgsConstructor
public class GameWebsocketHandler {

    private final GameEventWebSocketProcessor eventWebSocketProcessor;
    
    @MessageMapping("/queue/join")
    public void queuePlayer(QueueJoinEvent event) throws IOException {
        eventWebSocketProcessor.visit(event);
    }

    @MessageMapping("/queue/leave")
    public void queuePlayer(QueueLeaveEvent event) throws IOException {
        eventWebSocketProcessor.visit(event);
    }

    @MessageMapping("/match/pick")
    public void queuePlayer(GameMatchPickEvent event) throws IOException {
        eventWebSocketProcessor.visit(event);
    }
}