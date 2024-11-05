package mad.com.rpsmanager.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaUserEntity;

public interface JpaUserRepository extends CrudRepository<JpaUserEntity, Long> {
    
    Optional<JpaUserEntity> findByEmail(String email);
    Optional<JpaUserEntity> findByAlias(String alias);

}
