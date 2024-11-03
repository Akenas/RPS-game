package mad.com.rpsmanager.domain.model.game;

import lombok.Data;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;

/**
 * Represents a specific game mode with a unique identifier, type, associated ruleset, and name.
 */
@Data
public class GameMode {
    
    public enum TYPE{

        /**
         * Represents an online game mode.
         */
        ONLINE,

        /**
         * Represents an offline game mode.
         */
        OFFLINE
    }

    /**
     * Unique identifier for this game mode.
     */
    private final int id;

    /**
     * Type of the game mode, indicating if it is online or offline.
     */
    private final TYPE type;

    /**
     * The ruleset that defines the gameplay mechanics for this game mode.
     */
    private final Ruleset ruleset;

    /**
     * Name of the game mode.
     */
    private final String name;


}
