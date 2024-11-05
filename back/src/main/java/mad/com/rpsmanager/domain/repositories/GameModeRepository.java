package mad.com.rpsmanager.domain.repositories;

import java.util.List;
import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.GameMode;

public interface GameModeRepository {
    
    List<GameMode> findAll();
    Optional<GameMode> findById(int id);
    Optional<GameMode> findByName(String name);
    List<GameMode> findByType(String type);
    GameMode save(GameMode gameMode);
}
