package mad.com.rpsmanager.domain.transients.events;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import lombok.Data;
import lombok.NoArgsConstructor;
import mad.com.rpsmanager.domain.transients.events.game.GameForfeitEvent;
import mad.com.rpsmanager.domain.transients.events.game.GameMatchPickEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueJoinEvent;
import mad.com.rpsmanager.domain.transients.events.queues.QueueLeaveEvent;
import mad.com.rpsmanager.service.game.events.GameServiceVisitor;


/**
 * Class representing a generic application event.
 * See {@link QueueJoinEvent}, {@link QueueLeaveEvent}, {@link GameMatchPickEvent},
 */
@JsonTypeInfo(include = As.EXISTING_PROPERTY, use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = QueueJoinEvent.class, name = "queue-join"),
    @JsonSubTypes.Type(value = QueueLeaveEvent.class, name = "queue-leave"),
    @JsonSubTypes.Type(value = GameMatchPickEvent.class, name = "match-pick"),
    @JsonSubTypes.Type(value = GameForfeitEvent.class, name = "match-forfeit"),
})
@Data
@NoArgsConstructor
public abstract class GameManagerEvent {

    /**
     * 
     * @param visitor The class implementing {@GameServiceVisitor} that will process the event.
     * @throws IOException
     */
    public abstract void accept(GameServiceVisitor visitor) throws IOException;
}