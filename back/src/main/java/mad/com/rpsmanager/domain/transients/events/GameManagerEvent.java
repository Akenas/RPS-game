package mad.com.rpsmanager.domain.transients.events;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import lombok.Data;
import lombok.NoArgsConstructor;
import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;
import mad.com.rpsmanager.service.game.events.GameServiceVisitor;

@JsonTypeInfo(include = As.EXISTING_PROPERTY, use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = QueueJoinEvent.class, name = "queue-join"),
    @JsonSubTypes.Type(value = QueueLeaveEvent.class, name = "queue-leave")
})
@Data
@NoArgsConstructor
public abstract class GameManagerEvent {

    public abstract void accept(GameServiceVisitor visitor) throws IOException;
}