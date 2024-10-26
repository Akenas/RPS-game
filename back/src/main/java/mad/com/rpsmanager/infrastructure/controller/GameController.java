package mad.com.rpsmanager.infrastructure.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.service.game.GameService;

/**
 * Controller class for handling application requests.
 * <p>
 * This class provides an endpoint to verify the basic functionality of the application.
 * </p>
 */
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    

    private final GameService service;

    /**
     * Handles GET requests to the game modes URL ("/game/modes").
     *
     * @return a List of the available game modes. See  {@link GameMode}
     */
    @GetMapping("/modes")
    public List<GameMode> getGameModes(){
        return service.getGameModes();
    }
}
