package mad.com.rpsmanager.service.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.domain.transients.users.LoginUserDto;
import mad.com.rpsmanager.domain.transients.users.RegisterUserDto;

@RequiredArgsConstructor
public class AuthenticationService {
    
    private final CustomInMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public User signup(RegisterUserDto input) {
        User user = new User(input.getAlias(),input.getEmail(),passwordEncoder.encode(input.getPassword()));
            
        inMemoryUserDetailsManager.createUser(user);
        return (User)  inMemoryUserDetailsManager.loadUserByUsername(input.getEmail());
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return (User) inMemoryUserDetailsManager.loadUserByUsername(input.getEmail());
    }
}
