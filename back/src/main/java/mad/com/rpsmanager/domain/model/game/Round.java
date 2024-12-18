package mad.com.rpsmanager.domain.model.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

/**
 * Represents a single round in a game with a customizable ruleset.
 */
@RequiredArgsConstructor
public class Round {
    
    @Getter
    @Setter
    private long id;
    /**
     * The ruleset options size used for this round.
     */
    private final int rulesetOptionsSize;

    @Getter
    @Setter
    private boolean completed;
    /**
     * The result of the round, represented as an integer:
     * 0 if it's a tie,
     * 1 if Player 1 wins,
     * 2 if Player 2 wins.
     */
    @Getter
    @Setter
    private int winner = -1;

    /**
     * The choice made by Player 1, represented as a {@link RulesetOption}.
     */
    @Setter
    @Getter
    private RulesetOption player1Pick;

    /**
     * The choice made by Player 2, represented as a {@link RulesetOption}.
     */
    @Setter
    @Getter
    private RulesetOption player2Pick;

    /**
     * Determines the winner between two players in a game with a customizable ruleset.
     * @return An integer representing the result of the game:
     *         0 if it's a tie,
     *         1 if Player 1 wins,
     *         2 if Player 2 wins.
     */
    public int determineWinner(){
        int result = (rulesetOptionsSize + player1Pick.ordinal() - player2Pick.ordinal()) % rulesetOptionsSize;
        if (result == 0) {
            winner = 0;
        } else if (result <= (rulesetOptionsSize - 1) / 2) {
            winner = 1;
        } else {
            winner = 2;
        }
        this.completed = winner != 0;
        return this.winner;
    }

    public boolean boothPlayersPicked(){
        return this.player1Pick != null && this.player2Pick != null;
    }
}
