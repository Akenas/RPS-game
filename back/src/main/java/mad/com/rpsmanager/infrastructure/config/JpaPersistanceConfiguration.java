package mad.com.rpsmanager.infrastructure.config;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mad.com.rpsmanager.domain.repositories.UserRepository;
import mad.com.rpsmanager.infrastructure.persistence.UserRepositoryImpl;
import mad.com.rpsmanager.infrastructure.persistence.jpa.users.JpaUserRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.users.UserMapper;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("mad.com.rpsmanager.infrastructure.persistance")
@EntityScan("mad.com.rpsmanager.infrastructure.persistance")
public class JpaPersistanceConfiguration {
 
    
    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository){
        return new UserRepositoryImpl(jpaUserRepository);
    }

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }
}
