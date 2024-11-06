package mad.com.rpsmanager.infrastructure.persistence.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaRulesetEntity;

public interface JpaRulesetRepository  extends CrudRepository<JpaRulesetEntity, Integer>{
    

     List<JpaRulesetEntity> findAll();
}
