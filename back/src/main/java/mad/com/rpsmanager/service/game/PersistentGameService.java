package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;
import mad.com.rpsmanager.domain.repositories.GameMatchRepository;
import mad.com.rpsmanager.domain.repositories.GameModeRepository;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;

@RequiredArgsConstructor
public class PersistentGameService extends BasicGameService{

    private final GameModeRepository gameModeRepository;
    private final PlayerRepository playerRepository;
    private final GameMatchRepository gameMatchRepository;


    @Override
    public List<GameMode> getGameModes() {
       
        return gameModeRepository.findAll();
    }

    @Override
    public boolean setPlayerConnected(Player player) {
        
        player.setConnected(true);
        return playerRepository.save(player).isConnected();
    }

    @Override
    public boolean setPlayerDisconnected(long playerId) {
        
        Optional<Player> optPlayer = playerRepository.findById(playerId);
        if(optPlayer.isPresent()){
            Player player = optPlayer.get();
            player.setConnected(false);
            return !playerRepository.save(player).isConnected();
        }else return false;
        
    }

    @Override
    public List<Player> getConnectedPlayers() {
       return playerRepository.findByConnectedTrue();
    }

    @Override
    public Optional<GameMatch> computeMatchRound(String matchId, long playerId, int pick) {
        Optional<GameMatch> optMatch = gameMatchRepository.findById(matchId);

         if(optMatch.isPresent()){
            GameMatch match = optMatch.get();
            match.computeOngoingRound(RulesetOption.values()[pick], playerId);
           
            if(match.isOngoing())
                match.createRound(); 

            match = gameMatchRepository.save(match);

            return Optional.of(match);
        } else return Optional.empty();
    }

    @Override
    protected GameMatch createGameMatch(Player player, Player opponent, GameMode mode) {
        GameMatch match = new GameMatch(player, opponent, mode);
        return gameMatchRepository.save(match);
    }

    @Override
    protected Optional<GameMode> getGameModeById(int id) {
        return gameModeRepository.findById(id);
    }

    @Override
    protected Optional<Player> getPlayerById(long id) {
       return playerRepository.findById(id);
    }

    @Override
    public Optional<Player> getPlayerByAlias(String alias) {
        return playerRepository.findByAlias(alias);
    }

    @Override
    public Player createPlayer(String alias) {
       return playerRepository.save(new BasicPlayer(0,alias)); 
    }
}
