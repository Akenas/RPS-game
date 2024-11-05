package mad.com.rpsmanager.domain.repositories;

import java.util.List;
import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.GameMatch;

public interface GameMatchRepository {

    Optional<GameMatch> findById(Long id);
    List<GameMatch> findByPlayer1IdOrPlayer2Id(Long id);
    List<GameMatch> findByGameModeId(Long id);
    List<GameMatch> findByIsOngoingTrue();
    
} 
