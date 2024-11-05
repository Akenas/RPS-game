package mad.com.rpsmanager.infrastructure.persistance;

import java.util.Optional;

import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.domain.model.users.UserRepository;
import mad.com.rpsmanager.infrastructure.persistance.jpa.users.JpaUserEntity;
import mad.com.rpsmanager.infrastructure.persistance.jpa.users.JpaUserRepository;
import mad.com.rpsmanager.infrastructure.persistance.jpa.users.UserMapper;


public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(UserMapper.INSTANCE::toDomain);
    }

    @Override
    public Optional<User> findByAlias(String alias) {
        return jpaUserRepository.findByAlias(alias).map(UserMapper.INSTANCE::toDomain);
    }

    @Override
    public User save(User user) {
        JpaUserEntity userEntity = UserMapper.INSTANCE.toEntity(user);
        JpaUserEntity savedEntity = jpaUserRepository.save(userEntity);
        return UserMapper.INSTANCE.toDomain(savedEntity);
    }
}
