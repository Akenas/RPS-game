package mad.com.rpsmanager.infrastructure.persistence.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameModeEntity;

public interface JpaGameModeRepository extends CrudRepository<JpaGameModeEntity, Integer>{

    List<JpaGameModeEntity> findAll();
    Optional<JpaGameModeEntity> findById(int id);
    Optional<JpaGameModeEntity> findByName(String name);
    List<JpaGameModeEntity> findByType(int type);
    
} 