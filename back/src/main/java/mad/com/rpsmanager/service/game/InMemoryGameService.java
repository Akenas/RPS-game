package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.WaitingPlayerWrapper;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;

public class InMemoryGameService implements GameService {
    
    private final Queue<WaitingPlayerWrapper> waitingPlayers = new ConcurrentLinkedQueue<>();

    public List<GameMode> getGameModes(){
        return List.of(new GameMode(1,TYPE.OFFLINE,new BasicRuleset(3),"BO3 vs IA"), 
                       new GameMode(2,TYPE.OFFLINE,new BasicRuleset(5),"BO5 vs IA"),
                       new GameMode(3,TYPE.ONLINE,new BasicRuleset(3),"BO3 vs PLAYER"),
                       new GameMode(4,TYPE.ONLINE,new BasicRuleset(5),"BO5 vs PLAYER"));
    }

    public boolean queuePlayer(Player player, GameMode mode){
        WaitingPlayerWrapper wrapper = new WaitingPlayerWrapper(player,mode);
        if(!waitingPlayers.contains(wrapper))
            return waitingPlayers.add(wrapper);
        else return false;
    }

    public Optional<Player> getOponent(Player player, GameMode mode){

        Optional<WaitingPlayerWrapper> wrapper = waitingPlayers.stream().filter(w-> w.getMode().getId() == mode.getId()).findFirst();
        if(wrapper.isPresent()){
            return Optional.of(wrapper.get().getPlayer());
        }else return Optional.empty();
    }

    public boolean removePlayerFromQueue(Player player,GameMode mode){

        return waitingPlayers.remove(new WaitingPlayerWrapper(player,mode));
    }
}
