package mad.com.rpsmanager.domain.model.game.ruleset;

import java.util.List;

/**
 * Represents the ruleset for a game, defining the available options and number of rounds to play.
 */
public interface Ruleset {

    /**
     * Enum representing the possible options in the ruleset.
     */
    public enum RulesetOption{
        ROCK,
        PAPER,
        SCISSORS,
    }
    
    /**
     * Returns the list of options available in this ruleset.
     *
     * @return A list of {@link RulesetOption} representing the available options.
     */
    List<RulesetOption> getRulesetOptions();

    /**
     * Returns the number of rounds to play.
     *
     * @return The number of rounds to be played.
     */
    int getRoundsToPlay();
}
