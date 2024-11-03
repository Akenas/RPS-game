package mad.com.rpsmanager.service.queues;

import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.players.Player;

/**
 * Interface for managing players in a game queue.
 * Provides methods for adding, removing, and getting opponents for players.
 */
public interface GameQueue {

    /**
     * Adds a player to the queue.
     *
     * @param player the player to be added to the queue.
     * @return true if the player was successfully added, false otherwise.
     */
    boolean queuePlayer(Player player);
    
    /**
     * Retrieves an opponent for the given player from the queue.
     *
     * @param player the player looking for an opponent.
     * @return an Optional containing the opponent if available, or an empty Optional if no opponent is found.
     */
    Optional<Player> getOpponent(Player player);

    /**
     * Removes a player from the queue.
     *
     * @param player the player to be removed from the queue.
     * @return true if the player was successfully removed, false otherwise.
     */
    boolean removePlayerFromQueue(Player player);
}