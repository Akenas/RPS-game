package mad.com.rpsmanager.service.game;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.service.queues.BasicGameQueue;
import mad.com.rpsmanager.service.queues.GameQueue;

public abstract class BasicGameService implements GameService{

    private final Map<Integer,GameQueue> queues = new ConcurrentHashMap<>();

    
     /**
     * Initializes the game service by setting up game queues for all available game modes.
     */
    @Override
    public void init() {
        getGameModes().forEach(mode -> queues.put(mode.getId(), new BasicGameQueue()));
    }

    @Override
    public Optional<GameMatch> queuePlayer(int playerId, int gameModeId) {


        Optional<Player> optPlayer = getPlayerById(playerId);
        Optional<GameMode> optMode = getGameModeById(gameModeId);

        if(optPlayer.isPresent() && optMode.isPresent()){
            Player player = optPlayer.get();
            GameMode mode = optMode.get();
            Optional<Player> optOpponent = getOpponent(player, mode);
            
            if(optOpponent.isPresent()){
                Player opponent = optOpponent.get();
                GameMatch match = createGameMatch(player, opponent, mode);
                return Optional.of(match);
            }else{
                GameQueue queue = getGameQueueForMode(mode);
                queue.queuePlayer(player);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean removePlayerFromQueue(int playerId, int modeId) {
        
        Optional<Player> optPlayer = getPlayerById(playerId);
        Optional<GameMode> optMode = getGameModeById(modeId);
        if(optPlayer.isPresent() && optMode.isPresent()){

            Player player = optPlayer.get();
            GameMode mode = optMode.get();
            GameQueue queue = getGameQueueForMode(mode);
            return queue.removePlayerFromQueue(player);
        }
        
        return false;
    }

     /**
     * Retrieves an opponent for the specified player in the given game mode.
     *
     * @param player the {@link Player} looking for an opponent.
     * @param mode the {@link GameMode} in which the opponent is to be found.
     * @return an {@link Optional} containing an opponent {@link Player} if available; 
     *         otherwise, an empty {@link Optional}.
     */
    private Optional<Player> getOpponent(Player player, GameMode mode) {
        
        GameQueue queue = getGameQueueForMode(mode);
        return queue.getOpponent(player);
    }

    
    private GameQueue getGameQueueForMode(GameMode mode){
        GameQueue queue = queues.get(mode.getId());
        if(queue != null){
            return queue;
        } else throw new UnsupportedOperationException("No queue found for game mode "+mode.getId());
    }

    protected abstract  GameMatch createGameMatch(Player player, Player opponent, GameMode mode);

    /**
     * Retrieves a GameMode by its id.
     *
     * @return a Optional of {@link GameMode} if found.
     */
    protected abstract  Optional<GameMode> getGameModeById(int id);

    /**
     * Retrieves a Player by its id.
     *
     * @return a Optional of {@link Player} if found.
     */
    protected abstract  Optional<Player> getPlayerById(int id);
}
