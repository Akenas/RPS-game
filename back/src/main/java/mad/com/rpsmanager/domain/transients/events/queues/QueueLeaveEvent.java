package mad.com.rpsmanager.domain.transients.events.queues;

import java.io.IOException;

import mad.com.rpsmanager.service.game.GameServiceVisitor;

public class QueueLeaveEvent extends QueueEvent{

    public QueueLeaveEvent(int modeId, int playerId) {
        super(modeId, playerId);
    }

    @Override
    public void accept(GameServiceVisitor visitor) throws IOException {
        visitor.visit(this);
    }
}
