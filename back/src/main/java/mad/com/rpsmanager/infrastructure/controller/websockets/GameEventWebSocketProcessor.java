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
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;
import mad.com.rpsmanager.domain.transients.events.GameManagerEvent;
import mad.com.rpsmanager.domain.transients.events.game.GameMatchPickEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;
import mad.com.rpsmanager.service.game.GameService;
import mad.com.rpsmanager.service.game.events.GameEventProcessor;

public class GameEventWebSocketProcessor extends GameEventProcessor {
    
    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final Optional<PlayerRepository> playerRepository;
    private final ObjectMapper mapper;

    public GameEventWebSocketProcessor(GameService gameService,Optional<PlayerRepository> playerRepository, ObjectMapper mapper) {
        super(gameService);
        this.playerRepository = playerRepository;
        this.mapper = mapper;
       
    }

    public void addSession(WebSocketSession session) throws Exception {
        
        User user = (User) session.getPrincipal();
        Optional<Player>  optPlayer;

        if(playerRepository.isPresent()){
            optPlayer = playerRepository.get().findByAlias(user.getAlias());
        }else{
            optPlayer = Optional.of(new BasicPlayer(user.getId(), user.getAlias()));
        }

        if(optPlayer.isPresent()){
            Player player = optPlayer.get();
            gameService.setPlayerConnected(player);
            sessions.put(player.getId(), session);
        }
    }

    public void removeSession(WebSocketSession session) throws Exception {
        
        User user = (User) session.getPrincipal();
        Optional<Player>  optPlayer;

        if(playerRepository.isPresent()){
            optPlayer = playerRepository.get().findByAlias(user.getAlias());
        }else{
            optPlayer = Optional.of(new BasicPlayer(user.getId(), user.getAlias()));
        }

        if(optPlayer.isPresent()){
            Player player = optPlayer.get();
            gameService.setPlayerDisconnected(player.getId());
            sessions.remove(player.getId());
        }
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
