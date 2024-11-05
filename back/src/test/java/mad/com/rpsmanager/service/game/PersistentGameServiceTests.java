package mad.com.rpsmanager.service.game;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.GameMode.TYPE;
import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;
import mad.com.rpsmanager.domain.repositories.GameMatchRepository;
import mad.com.rpsmanager.domain.repositories.GameModeRepository;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;
import mad.com.rpsmanager.domain.repositories.RulesetRepository;
import mad.com.rpsmanager.infrastructure.config.JpaPersistanceConfiguration;


@DataJpaTest
@Import(PersistentGameService.class) 
@ContextConfiguration(classes = JpaPersistanceConfiguration.class)
public class PersistentGameServiceTests {

    @Autowired
    private GameModeRepository gameModeRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameMatchRepository gameMatchRepository;

    @Autowired
    private RulesetRepository rulesetRepository;

    @Autowired
    private PersistentGameService gameService;

    private GameMode offlineMode;
    private GameMode onlineMode;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {

        Ruleset ruleset = new BasicRuleset(3, List.of(RulesetOption.ROCK, RulesetOption.PAPER, RulesetOption.SCISSORS));
        ruleset = rulesetRepository.save(ruleset);

        // Initialize and save game modes
        offlineMode = new GameMode(1, TYPE.OFFLINE, ruleset, "BO3 vs IA");
        onlineMode = new GameMode(2, TYPE.ONLINE, ruleset, "BO3 vs Player");

        offlineMode = gameModeRepository.save(offlineMode);
        onlineMode = gameModeRepository.save(onlineMode);

        // Initialize and save players
        player1 = new BasicPlayer(1, "TEST_USER_1");
        player2 = new BasicPlayer(2, "TEST_USER_2");

        player1 = playerRepository.save(player1);
        player2 = playerRepository.save(player2);
    }

    @Test
    public void doesGetGameModes_OK() {
        List<GameMode> modes = gameService.getGameModes();
        Assertions.assertNotNull(modes);
        Assertions.assertEquals(2, modes.size());
    }

    @Test
    public void doesSetPlayerConnected_OK() {
        boolean isConnected = gameService.setPlayerConnected(player1);
        Assertions.assertTrue(isConnected);

        Optional<Player> updatedPlayer = playerRepository.findById(player1.getId());
        Assertions.assertTrue(updatedPlayer.isPresent());
        Assertions.assertTrue(updatedPlayer.get().isConnected());
    }

    @Test
    public void doesSetPlayerDisconnected_OK() {
        gameService.setPlayerConnected(player1);
        boolean isDisconnected = gameService.setPlayerDisconnected(player1.getId());
        Assertions.assertTrue(isDisconnected);

        Optional<Player> updatedPlayer = playerRepository.findById(player1.getId());
        Assertions.assertTrue(updatedPlayer.isPresent());
        Assertions.assertFalse(updatedPlayer.get().isConnected());
    }

    @Test
    public void doesGetConnectedPlayers_OK() {
        gameService.setPlayerConnected(player1);
        gameService.setPlayerConnected(player2);

        List<Player> connectedPlayers = gameService.getConnectedPlayers();
        Assertions.assertNotNull(connectedPlayers);
        Assertions.assertEquals(2, connectedPlayers.size());
    }

    @Test
    public void doesComputeMatchRound_OK() {
        GameMatch match = new GameMatch(player1, player2, onlineMode).start().then().createRound();
        match = gameMatchRepository.save(match);
        String generatedMatchId = match.getId();
        Optional<GameMatch> result = gameService.computeMatchRound(generatedMatchId, player1.getId(), RulesetOption.ROCK.ordinal());
        Assertions.assertTrue(result.isPresent());

        GameMatch updatedMatch = result.get();
        Assertions.assertNotNull(updatedMatch);
        Assertions.assertEquals(generatedMatchId, updatedMatch.getId());
    }

    @Test
    public void doesCreateGameMatch_OK() {
        GameMatch match = gameService.createGameMatch(player1, player2, onlineMode);
        Assertions.assertNotNull(match);
        Assertions.assertEquals(onlineMode.getId(), match.getMode().getId());

        Optional<GameMatch> retrievedMatch = gameMatchRepository.findById(match.getId());
        Assertions.assertTrue(retrievedMatch.isPresent());
    }

    @Test
    public void doesGetGameModeById_OK() {
        Optional<GameMode> result = gameService.getGameModeById(offlineMode.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(offlineMode, result.get());
    }

    @Test
    public void doesGetPlayerById_OK() {
        Optional<Player> result = gameService.getPlayerById(player1.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(player1, result.get());
    }
}