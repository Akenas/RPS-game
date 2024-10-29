package mad.com.rpsmanager.service.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;

/**
 * In-memory implementation of the {@link GameService} interface.
 * Manages game modes, player queues, and opponent matching in memory.
 */
public class InMemoryGameService extends BasicGameService {
    
    
    private final Map<String, GameMatch> ongoingMatches = new ConcurrentHashMap<>();
    private final Map<Integer, Player> connectedPlayers = new ConcurrentHashMap<>();

    public List<GameMode> getGameModes(){
        return List.of(new GameMode(1,TYPE.OFFLINE,new BasicRuleset(3),"BO3 vs IA"), 
                       new GameMode(2,TYPE.OFFLINE,new BasicRuleset(5),"BO5 vs IA"),
                       new GameMode(3,TYPE.ONLINE,new BasicRuleset(3),"BO3 vs PLAYER"),
                       new GameMode(4,TYPE.ONLINE,new BasicRuleset(5),"BO5 vs PLAYER"));
    }

    @Override
    public Optional<GameMode> getGameModeById(int id) {

       return getGameModes().stream().filter(m -> m.getId() == id).findFirst();
    }

    @Override
    public Optional<Player> getPlayerById(int id) {
        if(connectedPlayers.containsKey(id))
            return Optional.of(connectedPlayers.get(id));
        else return Optional.empty();
    }

    @Override
    public List<Player> getConnectedPlayers() {

        return new ArrayList<>(connectedPlayers.values());
    }


    @Override
    public boolean setPlayerConnected(Player player) {
        return connectedPlayers.putIfAbsent(player.getId(), player) == null;
    }

    @Override
    public boolean setPlayerDisconnected(int playerId) {
        return connectedPlayers.remove(playerId) != null;
    }
 
    @Override
    protected GameMatch createGameMatch(Player player, Player opponent, GameMode mode) {
       
        GameMatch match = new GameMatch(UUID.randomUUID().toString(), player, opponent, mode.getRuleset());
        ongoingMatches.put(match.getId(), match);
        return match;
    }
}
