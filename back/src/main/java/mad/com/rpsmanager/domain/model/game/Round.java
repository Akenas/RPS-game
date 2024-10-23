package mad.com.rpsmanager.domain.model.game;

import lombok.Data;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

/**
 * Represents a single round in a game with a customizable ruleset.
 */
@Data
public class Round {
    
    /**
     * The ruleset used for this round.
     */
    private final Ruleset ruleset;

    /**
     * The result of the round, represented as an integer:
     * 0 if it's a tie,
     * 1 if Player 1 wins,
     * 2 if Player 2 wins.
     */
    private int winner;

    /**
     * Determines the winner between two players in a game with a customizable ruleset.
     *
     * @param player1Pick The choice made by Player 1, represented as a {@link RulesetOption}.
     * @param player2Pick The choice made by Player 2, represented as a {@link RulesetOption}.
     * @return An integer representing the result of the game:
     *         0 if it's a tie,
     *         1 if Player 1 wins,
     *         2 if Player 2 wins.
     */
    public int determineWinner(RulesetOption player1Pick, RulesetOption player2Pick){
        int result = (ruleset.getRulesetOptions().size() + player1Pick.ordinal() - player2Pick.ordinal()) % ruleset.getRulesetOptions().size();
        if (result == 0) {
            winner = 0;
        } else if (result <= (ruleset.getRulesetOptions().size() - 1) / 2) {
            winner = 1;
        } else {
            winner = 2;
        }

        return this.winner;
    }
}
