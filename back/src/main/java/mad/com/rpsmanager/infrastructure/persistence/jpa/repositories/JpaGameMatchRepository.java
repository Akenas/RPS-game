package mad.com.rpsmanager.infrastructure.persistence.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameMatchEntity;

public interface JpaGameMatchRepository  extends CrudRepository<JpaGameMatchEntity, String>{

    @Query("SELECT gm FROM JpaGameMatchEntity gm WHERE gm.player1.id = :playerId OR gm.player2.id = :playerId")
    List<JpaGameMatchEntity> findByPlayer1IdOrPlayer2Id(@Param("playerId") Long id);
    List<JpaGameMatchEntity> findByModeId(int id);
    List<JpaGameMatchEntity> findByOngoingTrue();
} 
