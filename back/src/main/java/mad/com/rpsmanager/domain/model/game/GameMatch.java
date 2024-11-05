package mad.com.rpsmanager.domain.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

/**
 * Represents a game match between two players, including rounds, ruleset, and
 * match status.
 * This class allows for managing and computing the outcomes of rounds and the
 * overall match.
 */
@RequiredArgsConstructor
public class GameMatch {

    /**
     * The id of the match.
     */
    @Getter
    private final String id;

    /**
     * The {@link Player} on the first slot of the match.
     * Usually the player that joined the queue last.
     */
    @Getter
    private final Player player1;

    /**
     * The {@link Player} on the second slot of the match.
     * Usually the player that is found as opponent.
     */
    @Getter
    private final Player player2;

    /**
     * The {@link GameMode} used for this game match.
     */
    @Getter
    private final GameMode mode;

    @Getter
    private int winner;

    /**
     * The list of {@link Round}s in this game match.
     */
    @Getter
    private List<Round> rounds = new ArrayList<>();

    /**
     * Indicates whether the match is currently ongoing.
     */
    @Getter
    private boolean ongoing;

    /**
     * Starts the game match.
     *
     * @return The current instance of GameMatch, allowing for method chaining.
     */
    public GameMatch start() {
        this.ongoing = true;
        return this;
    }

    /**
     * Finishes the game match.
     *
     * @return The current instance of GameMatch, allowing for method chaining.
     */
    public GameMatch finish() {
        this.ongoing = false;
        return this;
    }

    /**
     * Computes the result of the ongoing round with the given choices for Player 1 and Player 2.
     *
     * @param player1Pick The choice made by Player 1, represented as a {@link RulesetOption}.
     * @param player2Pick The choice made by Player 2, represented as a {@link RulesetOption}.
     * @return The current instance of GameMatch, allowing for method chaining.
     * @throws UnsupportedOperationException if there are no ongoing rounds to compute.
     */
    public GameMatch computeOngoingRound(RulesetOption playerPick, long playerId) {
        Optional<Round> optRound = rounds.stream().filter(r -> !r.isCompleted()).findFirst();
        if (optRound.isPresent()) {

            Round round = optRound.get();

            if(playerId == player1.getId()){
                round.setPlayer1Pick(playerPick);
            }
            if(playerId == player2.getId()){
                round.setPlayer2Pick(playerPick);
            }else if(mode.getType().equals(TYPE.OFFLINE)){
                RulesetOption machinePick = RulesetOption.values()[ThreadLocalRandom.current().nextInt(0,
                mode.getRuleset().getRulesetOptions().size())];
                round.setPlayer2Pick(machinePick);
            }
            if(round.boothPlayersPicked()){
                round.determineWinner();
                computeMatchResult();
            }
            
            return this;
        } else
            throw new UnsupportedOperationException("Can not compute round. No ongoing rounds for this match");
    }

    /**
     * Computes the result of the ongoing round with the given choice for Player 1 and a random choice for Player 2.
     * Used on solo games against IA
     * @param player1Pick The choice made by Player 1.
     * @return The current instance of GameMatch, allowing for method chaining.
     */
    public GameMatch computeOngoingRound(RulesetOption player1Pick) {

       
        return computeOngoingRound(player1Pick, player1.getId());
    }

    /**
     * Creates a new round for the game match if the match is ongoing and the maximum number of rounds has not been reached.
     *
     * @return The current instance of GameMatch, allowing for method chaining.
     * @throws UnsupportedOperationException if the match has finished or the maximum number of rounds has been reached.
     */
    public GameMatch createRound() {
        if (ongoing && rounds.size() < mode.getRuleset().getRoundsToPlay()) {
            Round newRound = new Round(mode.getRuleset().getRulesetOptions().size());
            rounds.add(newRound);
            return this;
        } else
            throw new UnsupportedOperationException(
                    "Can not add more rounds. Match has finnished or has the max rounds available for the ruleset selected");

    }

    /**
     * Computes the match result for best-of-3 (bo3) or best-of-5 (bo5) matches.
     *
     * @return An integer representing the match result:
     *         0 if the match is ongoing or tied,
     *         1 if Player 1 wins the match,
     *         2 if Player 2 wins the match.
     */
    private void computeMatchResult() {

        int player1Wins = 0;
        int player2Wins = 0;

        for (Round round : rounds) {
            if (round.isCompleted()) {
                int winner = round.getWinner();
                if (winner == 1) {
                    player1Wins++;
                } else if (winner == 2) {
                    player2Wins++;
                }
            }
        }

        int roundsToWin = (mode.getRuleset().getRoundsToPlay() / 2) + 1; // e.g., 2 wins for bo3, 3 wins for bo5
        if (player1Wins >= roundsToWin) {
            this.winner = 1; 
            finish();
        } else if (player2Wins >= roundsToWin) {
            this.winner = 2;
            finish();
        }
    }

    /**
     * Placeholder method to allow for chaining operations in a fluent interface style.
     *
     * @return The current instance of GameMatch, allowing for method chaining.
     */
    public GameMatch then() {
        return this;
    }

    public boolean isOffline(){
        return this.mode.getType().equals(TYPE.OFFLINE);
    }
}
