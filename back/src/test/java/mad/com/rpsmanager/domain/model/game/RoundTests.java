package mad.com.rpsmanager.domain.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

public class RoundTests {
    
    private static final int ROUNDS_NUMBER = 1;

    @Test
    public void doesDetermineWinner_RockBeatsScissors(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER).getRulesetOptions().size());
        round.setPlayer1Pick(RulesetOption.ROCK);
        round.setPlayer2Pick(RulesetOption.SCISSORS);
        int winner = round.determineWinner();
        Assertions.assertEquals(1, winner,"ROCK must beat SCISSORS");
    }

    @Test
    public void doesDetermineWinner_ScissorsBeatPaper(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER).getRulesetOptions().size());
        round.setPlayer1Pick(RulesetOption.SCISSORS);
        round.setPlayer2Pick(RulesetOption.PAPER);
        int winner = round.determineWinner();
        Assertions.assertEquals(1, winner,"SCISSORS must beat PAPER");
    }

    @Test
    public void doesDetermineWinner_PaperBeatsRock(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER).getRulesetOptions().size());
        round.setPlayer1Pick(RulesetOption.PAPER);
        round.setPlayer2Pick(RulesetOption.ROCK);
        int winner = round.determineWinner();
        Assertions.assertEquals(1, winner,"PAPER must beat ROCK");
    }

    @Test
    public void doesDetermineWinner_Tie(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER).getRulesetOptions().size());
        round.setPlayer1Pick(RulesetOption.PAPER);
        round.setPlayer2Pick(RulesetOption.PAPER);
        int winner = round.determineWinner();
        Assertions.assertEquals(0, winner,"When both options are equal, round should be a tie");
    }

    @Test
    public void doesDetermineWinner_PlayerTwoWins(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER).getRulesetOptions().size());
        round.setPlayer1Pick(RulesetOption.PAPER);
        round.setPlayer2Pick(RulesetOption.SCISSORS);
        int winner = round.determineWinner();
        Assertions.assertEquals(2, winner,"SCISSORS must beat PAPER. Player 2 should win in this scenario");
    }
}
