package mad.com.rpsmanager.infrastructure.persistence.jpa;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.domain.repositories.UserRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaUserEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.mappers.UserMapper;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaUserRepository;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

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

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(UserMapper.INSTANCE::toDomain);
    }
}
