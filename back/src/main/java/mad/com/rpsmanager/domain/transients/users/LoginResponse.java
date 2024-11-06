package mad.com.rpsmanager.domain.transients.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {
    
    private final String token;

    private final long expiresIn;
}
