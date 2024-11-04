package mad.com.rpsmanager.infrastructure.persistance.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.domain.repositories.UserRepository;

public interface JpaUserRepository extends UserRepository, CrudRepository<User, Long> {
    


}
