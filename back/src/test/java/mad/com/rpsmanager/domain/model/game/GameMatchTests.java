package mad.com.rpsmanager.domain.model.game;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

public class GameMatchTests {
    
    private static final int ROUNDS_NUMBER = 3;
    private final Player PLAYER_1 = new BasicPlayer(1, "TEST_USER_1");
    private final Player PLAYER_2 = new BasicPlayer(2, "TEST_USER_2");
    private final GameMode ONLINE_MODE = new GameMode(3, TYPE.ONLINE, new BasicRuleset(ROUNDS_NUMBER,List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)), "BO3 vs Player");
    private final GameMode OFFLINE_MODE = new GameMode(1, TYPE.OFFLINE, new BasicRuleset(ROUNDS_NUMBER,List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)), "BO3 vs IA");
    
    @Test
    public void doesStartMatch(){

        GameMatch match = new GameMatch(UUID.randomUUID().toString(),PLAYER_1, PLAYER_2, ONLINE_MODE);
        match.start();
        Assertions.assertTrue(match.isOngoing());
    }

    @Test
    public void doesCreateInnitialRound_OK(){

        GameMatch match = new GameMatch(UUID.randomUUID().toString(),PLAYER_1, PLAYER_2, ONLINE_MODE);
        match.start().then().createRound();
        Assertions.assertEquals(1,match.getRounds().size());
    }

    @Test
    public void doesCreateRound_KO_Max_Rounds(){

        GameMatch match = new GameMatch(UUID.randomUUID().toString(),PLAYER_1, PLAYER_2,ONLINE_MODE);
        
        Assertions.assertThrows(UnsupportedOperationException.class, ()->  match.start().then().createRound().then().createRound().then().createRound().then().createRound());
    }

    @Test
    public void doesCreateRound_KO_Finished(){

        GameMatch match = new GameMatch(UUID.randomUUID().toString(),PLAYER_1, PLAYER_2, ONLINE_MODE);
        Assertions.assertThrows(UnsupportedOperationException.class, ()->   match.start().then().finish().then().createRound());
    }

    @Test
    public void doesComputeWinnerAgainstAI(){

        GameMatch match = new GameMatch(UUID.randomUUID().toString(),PLAYER_1, PLAYER_2, OFFLINE_MODE);
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

        GameMatch match = new GameMatch(UUID.randomUUID().toString(),PLAYER_1, PLAYER_2, ONLINE_MODE);
        match.start();

        match.createRound()
        .then()
        .computeOngoingRound(RulesetOption.SCISSORS, PLAYER_1.getId())
        .computeOngoingRound(RulesetOption.PAPER, PLAYER_2.getId());

        match.createRound()
        .then()
        .computeOngoingRound(RulesetOption.SCISSORS, PLAYER_1.getId())
        .computeOngoingRound(RulesetOption.ROCK, PLAYER_2.getId());

        match.createRound()
        .then()
        .computeOngoingRound(RulesetOption.ROCK, PLAYER_1.getId())
        .computeOngoingRound(RulesetOption.PAPER, PLAYER_2.getId());

        Assertions.assertFalse(match.isOngoing());
        Assertions.assertEquals(2, match.getWinner());
        
    }
}
