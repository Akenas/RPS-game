package mad.com.rpsmanager.service.game.events;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.service.game.GameService;

@RequiredArgsConstructor
public abstract class GameEventProcessor implements GameServiceVisitor {
    
    protected final GameService gameService;
}
