package mad.com.rpsmanager.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the Rock Paper Scissors Game Manager application.
 * <p>
 * This class configures security settings, such as requiring authentication for all requests
 * and enabling basic authentication.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * Configures the security filter chain, setting up HTTP security to require authentication
     * for all requests and enabling basic authentication.
     *
     * @param http the {@link HttpSecurity} to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
