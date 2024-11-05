package mad.com.rpsmanager.infrastructure.persistence.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameModeEntity;

public interface JpaGameModeRepository extends CrudRepository<JpaGameModeEntity, Integer>{

    Optional<GameMode> findById(int id);
    Optional<GameMode> findByName(String name);
    List<GameMode> findByType(int type);
    
} 