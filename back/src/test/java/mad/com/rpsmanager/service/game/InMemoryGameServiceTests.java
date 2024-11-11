package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

public class InMemoryGameServiceTests {

    private final GameMode OFFLINE_MODE = new GameMode(1, TYPE.OFFLINE, new BasicRuleset(3, List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)), "BO3 vs IA");
    private final GameMode ONLINE_MODE = new GameMode(3, TYPE.ONLINE, new BasicRuleset(3,List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS)), "BO3 vs Player");
    
    private final Player PLAYER_1 = new BasicPlayer(1, "TEST_USER_1");
    private final Player PLAYER_2 = new BasicPlayer(2, "TEST_USER_2");

    @Test
    public void doesGetGameModes_OK() {
        
        GameService service = new InMemoryGameService();
        service.init();

        List<GameMode> modes = service.getGameModes();
        Assertions.assertNotNull(modes);
        Assertions.assertEquals(4, modes.size());
    }

    @Test
    public void doesQueuePlayer_OK() {

        GameService service = new InMemoryGameService();
        service.init();
        service.setPlayerConnected(PLAYER_1);

        Optional<GameMatch> optMatch = service.queuePlayer(PLAYER_1.getId(),ONLINE_MODE.getId());
        Assertions.assertFalse(optMatch.isPresent());
    }

    @Test
    public void doesRemovePlayerFromQueue_OK() {
        
        GameService service = new InMemoryGameService();
        service.init();
        service.createPlayer(PLAYER_1.getAlias());

        service.queuePlayer(PLAYER_1.getId(),ONLINE_MODE.getId());
        boolean rs = service.removePlayerFromQueue(PLAYER_1.getId(),ONLINE_MODE.getId());
        Assertions.assertTrue(rs);
    }

    @Test
    public void doesRemovePlayerFromQueue_OK_DONT_REMOVE() {
        GameService service = new InMemoryGameService();
        service.init();
        service.setPlayerConnected(PLAYER_1);

        service.queuePlayer(PLAYER_1.getId(),OFFLINE_MODE.getId());
        boolean rs = service.removePlayerFromQueue(PLAYER_2.getId(),OFFLINE_MODE.getId());
        Assertions.assertFalse(rs);
    }


    @Test
    public void doesGetOponent_OK() {

        GameService service = new InMemoryGameService();
        service.init();
        service.createPlayer(PLAYER_1.getAlias());
        service.createPlayer(PLAYER_2.getAlias());

        Optional<GameMatch> optMatch1 =service.queuePlayer(PLAYER_1.getId(), ONLINE_MODE.getId());
        Optional<GameMatch> optMatch2 = service.queuePlayer(PLAYER_2.getId(), ONLINE_MODE.getId());
        
        Assertions.assertFalse(optMatch1.isPresent());
        Assertions.assertTrue(optMatch2.isPresent());
        Assertions.assertEquals(PLAYER_1, optMatch2.get().getPlayer2());
    }
}
