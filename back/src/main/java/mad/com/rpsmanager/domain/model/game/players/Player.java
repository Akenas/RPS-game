package mad.com.rpsmanager.domain.model.game.players;

/**
 * Represents a player with an ID, alias, and rating.
 * The player rating can be computed based on specific game logic.
 */
public interface Player {

    /**
     * Retrieves the unique identifier of the player.
     *
     * @return the player's ID.
     */
    int getId();

     /**
     * Retrieves the alias or nickname of the player.
     *
     * @return the player's alias.
     */
    String getAlias();

    /**
     * Retrieves the current rating of the player.
     *
     * @return the player's rating.
     */
    int getRating();

    /**
     * Computes and updates the player's rating based on gameplay outcomes.
     */
    void computeRating();
}
