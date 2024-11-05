package mad.com.rpsmanager.infrastructure.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.repositories.GameMatchRepository;
import mad.com.rpsmanager.infrastructure.config.JpaPersistanceConfiguration;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameMatchEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameModeEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaPlayerEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaRulesetEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.mappers.GameMatchMapper;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaGameMatchRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaGameModeRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaPlayerRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaRulesetRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(GameMatchRepositoryImpl.class)
@ContextConfiguration(classes = JpaPersistanceConfiguration.class)
public class GameMatchRepositoryImplTest {

    @Autowired
    private JpaGameMatchRepository jpaGameMatchRepository;

    @Autowired
    private JpaGameModeRepository jpaGameModeRepository;

    @Autowired
    private JpaPlayerRepository jpaPlayerRepository;

    @Autowired
    private JpaRulesetRepository jpaRulesetRepository;

    @Autowired
    private GameMatchRepository gameMatchRepository;

   

    private JpaPlayerEntity player1;
    private JpaPlayerEntity player2;
    private JpaGameModeEntity gameMode;
    private JpaGameMatchEntity matchEntity;
    private JpaRulesetEntity rulesetEntity;

    @BeforeEach
    public void setup() {
        // Initialize test data
        player1 = new JpaPlayerEntity();
        player1.setAlias("player1");
        player1 = jpaPlayerRepository.save(player1);

        player2 = new JpaPlayerEntity();
        player2.setAlias("player2");
        player2 = jpaPlayerRepository.save(player1);


        rulesetEntity = new JpaRulesetEntity();
        rulesetEntity.setRoundsToPlay(3);
        rulesetEntity.setOptions("ROCK,PAPER,SCISSORS");

        rulesetEntity = jpaRulesetRepository.save(rulesetEntity);

        gameMode = new JpaGameModeEntity();
        gameMode.setName("Test Mode");
        gameMode.setRuleset(rulesetEntity);
        gameMode.setType(GameMode.TYPE.OFFLINE.ordinal());
        gameMode = jpaGameModeRepository.save(gameMode);

        matchEntity = new JpaGameMatchEntity();
        matchEntity.setPlayer1(player1);
        matchEntity.setPlayer2(player2);
        matchEntity.setMode(gameMode);
        matchEntity.setOngoing(true);
        matchEntity = jpaGameMatchRepository.save(matchEntity);
    }

    @Test
    public void testFindById() {
        Optional<GameMatch> result = gameMatchRepository.findById(matchEntity.getId());
        assertTrue(result.isPresent());
        assertEquals(matchEntity.getId(), result.get().getId());
    }

    @Test
    public void testFindByPlayer1IdOrPlayer2Id() {
        List<GameMatch> result = gameMatchRepository.findByPlayer1IdOrPlayer2Id(player1.getId());
        assertEquals(1, result.size());
        assertEquals(matchEntity.getId(), result.get(0).getId());
    }

    @Test
    public void testFindByModeId() {
        List<GameMatch> result = gameMatchRepository.findByModeId(gameMode.getId());
        assertEquals(1, result.size());
        assertEquals(matchEntity.getId(), result.get(0).getId());
    }

    @Test
    public void testFindByOngoingTrue() {
        List<GameMatch> result = gameMatchRepository.findByOngoingTrue();
        assertEquals(1, result.size());
        assertEquals(matchEntity.getId(), result.get(0).getId());
    }

    @Test
    public void testSave() {
        GameMatch match = GameMatchMapper.INSTANCE.toDomain(matchEntity);
        match.finish();

        GameMatch savedMatch = gameMatchRepository.save(match);

        assertEquals(matchEntity.getId(), savedMatch.getId());
        assertEquals(false, savedMatch.isOngoing());
    }
}
