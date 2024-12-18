package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.players.Player;

/**
 * Service interface for managing game-related operations such as retrieving game modes,
 * queuing players, and matching opponents.
 */
public interface GameService {
    
    /**
     * Initializes the game service, setting up necessary data or configurations.
     */
    public void init();

    /**
     * Retrieves the list of available game modes.
     *
     * @return a list of {@link GameMode} objects representing the game modes.
     */
    List<GameMode> getGameModes();

    /**
     * Queues a player for the specified game mode.
     *
     * @param player the {@link Player} to queue.
     * @param mode the {@link GameMode} in which the player wishes to play.
     * @return {@code true} if the player is successfully queued; {@code false} otherwise.
     */
    Optional<GameMatch> queuePlayer(long playerId, int gameModeId);



    /**
     * Removes a player from the queue for the specified game mode.
     *
     * @param player the {@link Player} to remove from the queue.
     * @param mode the {@link GameMode} from which the player is to be removed.
     * @return {@code true} if the player is successfully removed from the queue; {@code false} otherwise.
     */
    boolean removePlayerFromQueue(long playerId, int gameModeId);

    boolean setPlayerConnected(Player player);
    boolean setPlayerDisconnected(long playerId);
    List<Player> getConnectedPlayers();
    Optional<Player> getPlayerByAlias(String alias);
    Player createPlayer(String alias);
    Optional<GameMatch> computeMatchRound(String matchId, long playerId, int pick);

    public Optional<GameMatch> forfeitMatch(String matchId, long playerId);
}
