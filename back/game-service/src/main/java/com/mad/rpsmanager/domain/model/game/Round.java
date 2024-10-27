package mad.com.rpsmanager.domain.model.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

/**
 * Represents a single round in a game with a customizable ruleset.
 */
@RequiredArgsConstructor
public class Round {
    
    /**
     * The ruleset options size used for this round.
     */
    private final int rulesetOptionsSize;

    @Getter
    private boolean completed;
    /**
     * The result of the round, represented as an integer:
     * 0 if it's a tie,
     * 1 if Player 1 wins,
     * 2 if Player 2 wins.
     */
    @Getter
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
        int result = (rulesetOptionsSize + player1Pick.ordinal() - player2Pick.ordinal()) % rulesetOptionsSize;
        if (result == 0) {
            winner = 0;
        } else if (result <= (rulesetOptionsSize - 1) / 2) {
            winner = 1;
        } else {
            winner = 2;
        }
        this.completed = true;
        return this.winner;
    }
}
