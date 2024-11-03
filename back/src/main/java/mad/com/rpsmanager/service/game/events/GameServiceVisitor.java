package mad.com.rpsmanager.service.game.events;

import java.io.IOException;

import mad.com.rpsmanager.domain.transients.events.game.GameMatchPickEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;

public interface GameServiceVisitor {

    void visit(QueueJoinEvent event) throws IOException;
    void visit(QueueLeaveEvent event) throws IOException;
    void visit(GameMatchPickEvent event) throws IOException;
    
} 