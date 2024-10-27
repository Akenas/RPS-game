package mad.com.rpsmanager.service.queues;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mad.com.rpsmanager.domain.model.game.players.Player;

/**
 * Implementation of the {@link GameQueue} interface.
 * This class manages a queue of players waiting to join a non ranked game.
 */
public class BasicGameQueue implements GameQueue{
    
    private final Queue<Player> waitingPlayers = new ConcurrentLinkedQueue<>();
    
    public boolean queuePlayer(Player player){
         if(!waitingPlayers.contains(player))
            return waitingPlayers.add(player);
        else return false;
    }

     public Optional<Player> getOpponent(Player player){

        Player opponent = waitingPlayers.poll();

        if(opponent != null){
            return Optional.of(opponent);
        }else return Optional.empty();
    }

    public boolean removePlayerFromQueue(Player player){

        return waitingPlayers.remove(player);
    }
}
