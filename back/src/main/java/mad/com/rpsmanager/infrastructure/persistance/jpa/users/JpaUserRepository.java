package mad.com.rpsmanager.infrastructure.persistance.jpa.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<JpaUserEntity, Long> {
    
    Optional<JpaUserEntity> findByEmail(String email);
    Optional<JpaUserEntity> findByAlias(String alias);

}
