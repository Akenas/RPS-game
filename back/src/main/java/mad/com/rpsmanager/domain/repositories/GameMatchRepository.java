package mad.com.rpsmanager.domain.repositories;

import java.util.List;
import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.GameMatch;

public interface GameMatchRepository {

    Optional<GameMatch> findById(String id);
    List<GameMatch> findByPlayer1IdOrPlayer2Id(Long id);
    List<GameMatch> findByModeId(int id);
    List<GameMatch> findByOngoingTrue();
    GameMatch save(GameMatch match);
} 
