package mad.com.rpsmanager.service.game;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class GameEventProcessor implements GameServiceVisitor {
    
    protected final GameService gameService;
}
