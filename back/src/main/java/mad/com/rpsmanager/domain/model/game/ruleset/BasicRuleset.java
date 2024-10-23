package mad.com.rpsmanager.domain.model.game.ruleset;

import java.util.List;

import lombok.Data;

/**
 * Represents a basic ruleset for a game of Rock-Paper-Scissors.
 * This ruleset defines the number of rounds to play and the available options for each round.
 */
@Data
public class BasicRuleset implements Ruleset{

    /**
     * The number of rounds to play in this ruleset.
     */
    private final int roundsToPlay;

    /**
     * Returns the list of options available in this ruleset.
     *
     * @return A list of {@link RulesetOption} representing the available options.
     */
    @Override
    public List<RulesetOption> getRulesetOptions() {
        return List.of(RulesetOption.ROCK,RulesetOption.PAPER, RulesetOption.SCISSORS);
    }

    @Override
    public int getRoundsToPlay() {
        return this.roundsToPlay;
    }
    
}
