package mad.com.rpsmanager.domain.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

public class GameMatchTests {
    
    private static final int ROUNDS_NUMBER = 3;

    @Test
    public void doesStartMatch(){

        GameMatch match = new GameMatch(1, 2, new BasicRuleset(ROUNDS_NUMBER));
        match.start();
        Assertions.assertTrue(match.isOngoing());
    }

    @Test
    public void doesCreateInnitialRound_OK(){

        GameMatch match = new GameMatch(1, 2, new BasicRuleset(ROUNDS_NUMBER));
        match.start().then().createRound();
        Assertions.assertEquals(1,match.getRounds().size());
    }

    @Test
    public void doesCreateRound_KO_Max_Rounds(){

        GameMatch match = new GameMatch(1, 2, new BasicRuleset(1));
        
        Assertions.assertThrows(UnsupportedOperationException.class, ()->  match.start().then().createRound().then().createRound());
    }

    @Test
    public void doesCreateRound_KO_Finished(){

        GameMatch match = new GameMatch(1, 2, new BasicRuleset(ROUNDS_NUMBER));
        Assertions.assertThrows(UnsupportedOperationException.class, ()->   match.start().then().finish().then().createRound());
    }

    @Test
    public void doesComputeWinnerAgainstAI(){

        GameMatch match = new GameMatch(1, 2, new BasicRuleset(ROUNDS_NUMBER));
        match.start();

        while(match.isOngoing()){
            match.createRound()
            .computeOngoingRound(RulesetOption.SCISSORS);
        }
       
        Assertions.assertFalse(match.isOngoing());
        Assertions.assertNotEquals(0, match.getWinner());
        
    }

    @Test
    public void doesComputeWinnerAgainstPlayer(){

        GameMatch match = new GameMatch(1, 2, new BasicRuleset(ROUNDS_NUMBER));
        match.start();

        match.createRound()
        .then()
        .computeOngoingRound(RulesetOption.SCISSORS, RulesetOption.PAPER);

        match.createRound()
        .then()
        .computeOngoingRound(RulesetOption.SCISSORS, RulesetOption.ROCK);

        match.createRound()
        .then()
        .computeOngoingRound(RulesetOption.ROCK, RulesetOption.PAPER);

        Assertions.assertFalse(match.isOngoing());
        Assertions.assertEquals(2, match.getWinner());
        
    }
}
