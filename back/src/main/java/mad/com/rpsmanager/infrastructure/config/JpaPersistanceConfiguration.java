package mad.com.rpsmanager.infrastructure.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mad.com.rpsmanager.domain.repositories.GameMatchRepository;
import mad.com.rpsmanager.domain.repositories.GameModeRepository;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;
import mad.com.rpsmanager.domain.repositories.RulesetRepository;
import mad.com.rpsmanager.domain.repositories.UserRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.GameMatchRepositoryImpl;
import mad.com.rpsmanager.infrastructure.persistence.jpa.GameModeRepositoryImpl;
import mad.com.rpsmanager.infrastructure.persistence.jpa.PlayerRepositoryImpl;
import mad.com.rpsmanager.infrastructure.persistence.jpa.RulesetRepositoryImpl;
import mad.com.rpsmanager.infrastructure.persistence.jpa.UserRepositoryImpl;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaGameMatchRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaGameModeRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaPlayerRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaRulesetRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaUserRepository;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories("mad.com.rpsmanager.infrastructure.persistence.jpa")
@EntityScan("mad.com.rpsmanager.infrastructure.persistence.jpa")
@ConditionalOnProperty(name = "com.mad.rpsmanager.persistence.enabled", havingValue = "true", matchIfMissing = false)
public class JpaPersistanceConfiguration {
 
    
    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository){
        return new UserRepositoryImpl(jpaUserRepository);
    }

    @Bean
    public GameModeRepository gameModeRepository(JpaGameModeRepository jpaGameModeRepository){
        return new GameModeRepositoryImpl(jpaGameModeRepository);
    }

    @Bean
    public PlayerRepository playerRepository(JpaPlayerRepository jpaPlayerRepository){
        return new PlayerRepositoryImpl(jpaPlayerRepository);
    }

    @Bean
    public RulesetRepository rulesetRepository(JpaRulesetRepository jpaRulesetRepository){
        return new RulesetRepositoryImpl(jpaRulesetRepository);
    }

    @Bean
    public GameMatchRepository gameMatchRepository(JpaGameMatchRepository jpaGameMatchRepository){
        return new GameMatchRepositoryImpl(jpaGameMatchRepository);
    }
}
