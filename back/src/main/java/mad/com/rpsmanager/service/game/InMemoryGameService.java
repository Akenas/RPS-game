package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.service.queues.BasicGameQueue;
import mad.com.rpsmanager.service.queues.GameQueue;

/**
 * In-memory implementation of the {@link GameService} interface.
 * Manages game modes, player queues, and opponent matching in memory.
 */
public class InMemoryGameService implements GameService {
    
    private final Map<Integer,GameQueue> queues = new ConcurrentHashMap<>();

     /**
     * Initializes the game service by setting up game queues for all available game modes.
     */
    public void init(){
        getGameModes().forEach(mode -> queues.put(mode.getId(), new BasicGameQueue()));
    }

    public List<GameMode> getGameModes(){
        return List.of(new GameMode(1,TYPE.OFFLINE,new BasicRuleset(3),"BO3 vs IA"), 
                       new GameMode(2,TYPE.OFFLINE,new BasicRuleset(5),"BO5 vs IA"),
                       new GameMode(3,TYPE.ONLINE,new BasicRuleset(3),"BO3 vs PLAYER"),
                       new GameMode(4,TYPE.ONLINE,new BasicRuleset(5),"BO5 vs PLAYER"));
    }

    public boolean queuePlayer(Player player, GameMode mode) throws UnsupportedOperationException{

        GameQueue queue = getGameQueueForMode(mode);
        return queue.queuePlayer(player);
    }

    public Optional<Player> getOpponent(Player player, GameMode mode){

        GameQueue queue = getGameQueueForMode(mode);
        return queue.getOpponent(player);
    }

    public boolean removePlayerFromQueue(Player player,GameMode mode){

        GameQueue queue = getGameQueueForMode(mode);
        return queue.removePlayerFromQueue(player);
    }

    private GameQueue getGameQueueForMode(GameMode mode){
        GameQueue queue = queues.get(mode.getId());
        if(queue != null){
            return queue;
        } else throw new UnsupportedOperationException("No queue found for game mode "+mode.getId());
    }
}
