package mad.com.rpsmanager.service.game;

import java.util.List;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;

public class GameService {
    
    public List<GameMode> getGameModes(){
        return List.of(new GameMode(1,TYPE.OFFLINE,new BasicRuleset(3),"BO3 vs IA"), 
                       new GameMode(2,TYPE.OFFLINE,new BasicRuleset(5),"BO5 vs IA"),
                       new GameMode(3,TYPE.ONLINE,new BasicRuleset(3),"BO3 vs PLAYER"),
                       new GameMode(4,TYPE.ONLINE,new BasicRuleset(5),"BO5 vs PLAYER"));
    }

}
