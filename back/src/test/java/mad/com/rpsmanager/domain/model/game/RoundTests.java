package mad.com.rpsmanager.domain.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

public class RoundTests {
    
    private static final int ROUNDS_NUMBER = 1;

    @Test
    public void doesDetermineWinner_RockBeatsScissors(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER));
        int winner = round.determineWinner(RulesetOption.ROCK, RulesetOption.SCISSORS);
        Assertions.assertEquals(1, winner,"ROCK must beat SCISSORS");
    }

    @Test
    public void doesDetermineWinner_ScissorsBeatPaper(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER));
        int winner = round.determineWinner(RulesetOption.SCISSORS, RulesetOption.PAPER);
        Assertions.assertEquals(1, winner,"SCISSORS must beat PAPER");
    }

    @Test
    public void doesDetermineWinner_PaperBeatsRock(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER));
        int winner = round.determineWinner(RulesetOption.PAPER, RulesetOption.ROCK);
        Assertions.assertEquals(1, winner,"PAPER must beat ROCK");
    }

    @Test
    public void doesDetermineWinner_Tie(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER));
        int winner = round.determineWinner(RulesetOption.PAPER, RulesetOption.PAPER);
        Assertions.assertEquals(0, winner,"When both options are equal, round should be a tie");
    }

    @Test
    public void doesDetermineWinner_PlayerTwoWins(){
        Round round = new Round(new BasicRuleset(ROUNDS_NUMBER));
        int winner = round.determineWinner(RulesetOption.PAPER, RulesetOption.SCISSORS);
        Assertions.assertEquals(2, winner,"SCISSORS must beat PAPER. Player 2 should win in this scenario");
    }
}
