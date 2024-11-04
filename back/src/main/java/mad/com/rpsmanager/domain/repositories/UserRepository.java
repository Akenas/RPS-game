package mad.com.rpsmanager.domain.repositories;

import java.util.Optional;

import mad.com.rpsmanager.domain.model.users.User;

public interface UserRepository {
    
    Optional<User> findByEmail(String email);
    Optional<User> findByAlias(String alias);
    User save(User user);
}
