package mad.com.rpsmanager.domain.transients.events.queues;

import java.io.IOException;

import mad.com.rpsmanager.service.game.events.GameServiceVisitor;

public class QueueJoinEvent extends QueueEvent {



    public QueueJoinEvent(int modeId, int playerId) {
        super(modeId, playerId);
    }

    @Override
    public void accept(GameServiceVisitor visitor) throws IOException {
        visitor.visit(this);
    }
}
