package mad.com.rpsmanager.infrastructure.controller.websockets;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.domain.transients.events.GameManagerEvent;
import mad.com.rpsmanager.domain.transients.events.game.GameMatchPickEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;
import mad.com.rpsmanager.service.game.GameService;
import mad.com.rpsmanager.service.game.events.GameEventProcessor;

public class GameEventWebSocketProcessor extends GameEventProcessor {
    
    private final Map<Integer, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper mapper;

    public GameEventWebSocketProcessor(GameService gameService, ObjectMapper mapper) {
        super(gameService);
        this.mapper = mapper;
    }

    public void addSession(WebSocketSession session) throws Exception {
        
        //TODO When auth is set this must be done on login.
        int playerId = sessions.size()+1;
        gameService.setPlayerConnected(new BasicPlayer(playerId, "PLAYER_"+playerId));
        sessions.put(playerId, session);
        session.sendMessage(new TextMessage(playerId+" Session:"+session.getId()));
    }

    public void removeSession(WebSocketSession session) throws Exception {
        
        //CANT REMOVE SESSION WITHOUT PRINCIPAL
    }

    public void transformAndAccept(TextMessage message) throws IOException{
       
        GameManagerEvent request = mapper.readValue(message.getPayload(), GameManagerEvent.class);
        request.accept(this);
    }

    @Override
    public void visit(QueueJoinEvent event) throws IOException {
       
        Optional<GameMatch> optMatch = gameService.queuePlayer(event.getPlayerId(), event.getModeId());
        if(optMatch.isPresent()){
            GameMatch match = optMatch.get();

            WebSocketSession player1Session = sessions.get(match.getPlayer1().getId());
            TextMessage returnMessage = new TextMessage(mapper.writeValueAsString(match));
            player1Session.sendMessage(returnMessage);
            if(!match.isOffline()){
                WebSocketSession player2Session = sessions.get(match.getPlayer2().getId());
                player2Session.sendMessage(returnMessage);
            }
        }else{
            WebSocketSession queued = sessions.get(event.getPlayerId());
            queued.sendMessage(new TextMessage("No opponent found. Waiting for someone to join"));
        }
    }

    @Override
    public void visit(QueueLeaveEvent event) throws IOException {
        
        boolean result = gameService.removePlayerFromQueue(event.getPlayerId(), event.getModeId());
        WebSocketSession playerSession = sessions.get(event.getPlayerId());
        playerSession.sendMessage(new TextMessage(String.valueOf(result)));

    }

    @Override
    public void visit(GameMatchPickEvent event) throws IOException {

        Optional<GameMatch> optMatch = gameService.computeMatchRound(event.getMatchId(), event.getPlayerId(), event.getPick());
        if(optMatch.isPresent()){
            GameMatch match = optMatch.get();

            WebSocketSession player1Session = sessions.get(match.getPlayer1().getId());
            TextMessage returnMessage = new TextMessage(mapper.writeValueAsString(match));
            player1Session.sendMessage(returnMessage);
            if(!match.isOffline()){
                WebSocketSession player2Session = sessions.get(match.getPlayer2().getId());
                player2Session.sendMessage(returnMessage);
            }
            
            
        }
    }
}
