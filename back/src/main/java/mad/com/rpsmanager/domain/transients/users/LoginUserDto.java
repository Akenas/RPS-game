package mad.com.rpsmanager.domain.transients.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginUserDto {
    
    private final String email;
    
    private final String password;
}
