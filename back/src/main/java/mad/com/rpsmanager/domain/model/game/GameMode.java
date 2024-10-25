package mad.com.rpsmanager.domain.model.game;

import lombok.Data;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;

@Data
public class GameMode {
    
    public enum TYPE{
        ONLINE,
        OFFLINE
    }


    private final int id;
    private final TYPE type;
    private final Ruleset ruleset;
    private final String name;

    
}
