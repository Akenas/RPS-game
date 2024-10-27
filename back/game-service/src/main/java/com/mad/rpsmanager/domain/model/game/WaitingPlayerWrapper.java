package mad.com.rpsmanager.domain.model.game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import mad.com.rpsmanager.domain.model.game.players.Player;


//TODO Create QueueManager and delete this
@Getter 
@EqualsAndHashCode
public class WaitingPlayerWrapper {
    
    private Player player;
    private final GameMode mode;

    public WaitingPlayerWrapper(Player player, GameMode mode){
        this.player = player;
        this.mode = mode;
    }
}
