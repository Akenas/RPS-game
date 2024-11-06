package mad.com.rpsmanager.infrastructure.persistence.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaPlayerEntity;

public interface JpaPlayerRepository extends CrudRepository<JpaPlayerEntity,Long> {
    
    List<JpaPlayerEntity> findAll();
    Optional<JpaPlayerEntity> findByAlias(String name);
    List<JpaPlayerEntity> findByConnectedTrue();
}
