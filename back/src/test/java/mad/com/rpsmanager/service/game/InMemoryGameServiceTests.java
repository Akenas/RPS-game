package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;

public class InMemoryGameServiceTests {

    private final GameMode OFFLINE_MODE = new GameMode(1, TYPE.OFFLINE, new BasicRuleset(3), "BO3 vs IA");
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
        boolean rs = service.queuePlayer(new BasicPlayer(0, "TEST_USER"),OFFLINE_MODE);
        Assertions.assertTrue(rs);
    }

    @Test
    public void doesRemovePlayerFromQueue_OK() {
        
        GameService service = new InMemoryGameService();
        service.init();
        service.queuePlayer(new BasicPlayer(0, "TEST_USER"),OFFLINE_MODE);
        boolean rs = service.removePlayerFromQueue(new BasicPlayer(0, "TEST_USER"),OFFLINE_MODE);
        Assertions.assertTrue(rs);
    }

    @Test
    public void doesRemovePlayerFromQueue_OK_DONT_REMOVE() {
        GameService service = new InMemoryGameService();
        service.init();
        service.queuePlayer(new BasicPlayer(0, "TEST_USER"),OFFLINE_MODE);
        boolean rs = service.removePlayerFromQueue(new BasicPlayer(0, "TEST_USER"),OFFLINE_MODE);
        Assertions.assertTrue(rs);
    }


    @Test
    public void doesGetOponent_OK() {

        GameService service = new InMemoryGameService();
        service.init();
        Player player1 = new BasicPlayer(1, "TEST_USER");
        Player player2 = new BasicPlayer(2, "TEST_USER2");
        GameMode gameMode = new GameMode(3, TYPE.ONLINE, new BasicRuleset(3), "BO3 vs Player");

        service.queuePlayer(player1,gameMode);
        Optional<Player> matchedPlayer = service.getOpponent(player2, gameMode);
        
        Assertions.assertTrue(matchedPlayer.isPresent());
        Assertions.assertEquals(player1, matchedPlayer.get());
    }
}
