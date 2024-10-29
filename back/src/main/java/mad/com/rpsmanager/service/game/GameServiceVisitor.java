package mad.com.rpsmanager.service.game;

import java.io.IOException;

import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;

public interface GameServiceVisitor {

    void visit(QueueJoinEvent event) throws IOException;
    void visit(QueueLeaveEvent event) throws IOException;

} 