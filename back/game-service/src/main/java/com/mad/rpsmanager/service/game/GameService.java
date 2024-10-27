package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.players.Player;

/**
 * Service interface for managing game-related operations such as retrieving game modes,
 * queuing players, and matching opponents.
 */
public interface GameService {
    
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
    boolean queuePlayer(Player player, GameMode mode);

    /**
     * Retrieves an opponent for the specified player in the given game mode.
     *
     * @param player the {@link Player} looking for an opponent.
     * @param mode the {@link GameMode} in which the opponent is to be found.
     * @return an {@link Optional} containing an opponent {@link Player} if available; 
     *         otherwise, an empty {@link Optional}.
     */
    Optional<Player> getOponent(Player player, GameMode mode);

    /**
     * Removes a player from the queue for the specified game mode.
     *
     * @param player the {@link Player} to remove from the queue.
     * @param mode the {@link GameMode} from which the player is to be removed.
     * @return {@code true} if the player is successfully removed from the queue; {@code false} otherwise.
     */
    boolean removePlayerFromQueue(Player player,GameMode mode);
}
