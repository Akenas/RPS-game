package mad.com.rpsmanager.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.domain.transients.users.LoginResponse;
import mad.com.rpsmanager.domain.transients.users.LoginUserDto;
import mad.com.rpsmanager.domain.transients.users.RegisterUserDto;
import mad.com.rpsmanager.service.game.GameService;
import mad.com.rpsmanager.service.security.AuthenticationService;
import mad.com.rpsmanager.service.security.jwt.JwtService;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final GameService gameService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {

        if(!authenticationService.existsUserByAlias(registerUserDto.getAlias()) && !authenticationService.existsUserByEmail(registerUserDto.getEmail())){
            User registeredUser = authenticationService.signup(registerUserDto);
            gameService.createPlayer(registeredUser.getAlias());

            return ResponseEntity.ok(registeredUser);
        }else{
            return ResponseEntity.badRequest().build();
        }
        
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}