package mad.com.rpsmanager.infrastructure.controller.websockets;

import java.io.IOException;
import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.transients.events.game.GameForfeitEvent;
import mad.com.rpsmanager.domain.transients.events.game.GameMatchPickEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;
import mad.com.rpsmanager.service.game.GameService;
import mad.com.rpsmanager.service.game.events.GameEventProcessor;

public class GameEventWebSocketProcessor extends GameEventProcessor {
    
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper mapper;

    public GameEventWebSocketProcessor(GameService gameService,SimpMessagingTemplate messagingTemplate, ObjectMapper mapper) {
        super(gameService);
        this.messagingTemplate = messagingTemplate;
        this.mapper = mapper;    
    }

    @Override
    public void visit(QueueJoinEvent event) throws IOException {
       
        Optional<GameMatch> optMatch = gameService.queuePlayer(event.getPlayerId(), event.getModeId());
        
        if(optMatch.isPresent()){
            GameMatch match = optMatch.get();  
            String topicPath = "/queue/";
            messagingTemplate.convertAndSend(topicPath+match.getPlayer1().getId(), mapper.writeValueAsString(match));
            messagingTemplate.convertAndSend(topicPath+match.getPlayer2().getId(), mapper.writeValueAsString(match));
        }
    }

    @Override
    public void visit(QueueLeaveEvent event) throws IOException {
        
        boolean result = gameService.removePlayerFromQueue(event.getPlayerId(), event.getModeId());
        String topicPath = "/queue/" + event.getPlayerId();
        messagingTemplate.convertAndSend(topicPath, mapper.writeValueAsString(result));

    }

    @Override
    public void visit(GameMatchPickEvent event) throws IOException {

        Optional<GameMatch> optMatch = gameService.computeMatchRound(event.getMatchId(), event.getPlayerId(), event.getPick());
        if(optMatch.isPresent()){
            GameMatch match = optMatch.get();
            String topicPath = "/match/" + match.getId();
            messagingTemplate.convertAndSend(topicPath, mapper.writeValueAsString(match));
        }
    }

    @Override
    public void visit(GameForfeitEvent event) throws IOException {
        Optional<GameMatch> optMatch = gameService.forfeitMatch(event.getMatchId(), event.getPlayerId());
        if(optMatch.isPresent()){
            GameMatch match = optMatch.get();
            String topicPath = "/match/" + match.getId();
            messagingTemplate.convertAndSend(topicPath, mapper.writeValueAsString(match));
        }
    }

    
}
