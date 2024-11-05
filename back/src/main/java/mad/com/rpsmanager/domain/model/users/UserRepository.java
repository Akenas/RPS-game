package mad.com.rpsmanager.domain.model.users;

import java.util.Optional;

public interface UserRepository {
    
    Optional<User> findByEmail(String email);
    Optional<User> findByAlias(String alias);
    User save(User user);
}
