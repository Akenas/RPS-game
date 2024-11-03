package mad.com.rpsmanager.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerExceptionResolver;

import mad.com.rpsmanager.infrastructure.controller.AuthenticationController;
import mad.com.rpsmanager.service.security.AuthenticationService;
import mad.com.rpsmanager.service.security.CustomInMemoryUserDetailsManager;
import mad.com.rpsmanager.service.security.jwt.JwtAuthenticationFilter;
import mad.com.rpsmanager.service.security.jwt.JwtService;

@Configuration
public class AuthenticationConfig {
    

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time:3600000}")
    private long jwtExpiration;

    
    @Bean
    public UserDetailsService userDetailsService() {
       
        return new CustomInMemoryUserDetailsManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, HandlerExceptionResolver handlerExceptionResolver){
        return new JwtAuthenticationFilter(jwtService, userDetailsService(), handlerExceptionResolver);
    }

    @Bean
    public JwtService jwtService(){
        return new JwtService(secretKey, jwtExpiration);
    }

    @Bean
    public AuthenticationController authenticationController(JwtService jwtService, AuthenticationService authenticationService){
        return new AuthenticationController(jwtService, authenticationService);
    }

    @Bean
    public AuthenticationService authenticationService(CustomInMemoryUserDetailsManager inMemoryUserDetailsManager, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        return new AuthenticationService(inMemoryUserDetailsManager, passwordEncoder, authenticationManager);
    }
}
