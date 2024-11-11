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
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

/**
 * In-memory implementation of the {@link GameService} interface.
 * Manages game modes, player queues, and opponent matching in memory.
 */
public class InMemoryGameService extends BasicGameService {
    
    
    private final Map<String, GameMatch> ongoingMatches = new ConcurrentHashMap<>();
    private final Map<Long, Player> connectedPlayers = new ConcurrentHashMap<>();
    private final Map<Long, Player> existingPlayers = new ConcurrentHashMap<>();

    public List<GameMode> getGameModes(){
        return List.of(new GameMode(1,TYPE.OFFLINE,new BasicRuleset(3, List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)),"BO3 vs IA"), 
                       new GameMode(2,TYPE.OFFLINE,new BasicRuleset(5, List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)),"BO5 vs IA"),
                       new GameMode(3,TYPE.ONLINE,new BasicRuleset(3, List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)),"BO3 vs PLAYER"),
                       new GameMode(4,TYPE.ONLINE,new BasicRuleset(5, List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)),"BO5 vs PLAYER"));
    }

    @Override
    public Optional<GameMode> getGameModeById(int id) {

       return getGameModes().stream().filter(m -> m.getId() == id).findFirst();
    }

    @Override
    public Optional<Player> getPlayerById(long id) {
        if(existingPlayers.containsKey(id))
            return Optional.of(existingPlayers.get(id));
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
    public boolean setPlayerDisconnected(long playerId) {
        return connectedPlayers.remove(playerId) != null;
    }
 
    @Override
    protected GameMatch createGameMatch(Player player, Player opponent, GameMode mode) {
       
        GameMatch match = new GameMatch(UUID.randomUUID().toString(), player, opponent, mode);
        ongoingMatches.put(match.getId(), match);
        return match;
    }

    @Override
    public Optional<GameMatch> computeMatchRound(String matchId, long playerId, int pick) {
        GameMatch match = ongoingMatches.get(matchId);
        if(match != null){
            match.computeOngoingRound(RulesetOption.values()[pick], playerId);
            if(!match.isOngoing())
                ongoingMatches.remove(matchId);
            else match.createRound();
            return Optional.of(match);
        } else return Optional.empty();

    }

    @Override
    public Optional<Player> getPlayerByAlias(String alias) {
        return connectedPlayers.values().stream().filter(p-> p.getAlias().equals(alias)).findFirst();
    }

    @Override
    public Player createPlayer(String alias) {
        long id = existingPlayers.size() +1;
        Player player = new BasicPlayer(id, alias);
        existingPlayers.put(id, player);
        setPlayerConnected(player);
        return player;
    }
}
