package mad.com.rpsmanager.domain.transients.events;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import mad.com.rpsmanager.domain.transients.events.queues.QueueEvent;
import mad.com.rpsmanager.service.game.events.GameServiceVisitor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = QueueEvent.class, name = "queue")
})
@Data
public abstract class GameManagerEvent {

    public abstract void accept(GameServiceVisitor visitor) throws IOException;
}