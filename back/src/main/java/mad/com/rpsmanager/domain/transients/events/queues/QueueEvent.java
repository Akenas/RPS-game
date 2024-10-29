package mad.com.rpsmanager.domain.transients.events.queues;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.transients.events.GameManagerEvent;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "subtype")
@JsonSubTypes({
    @JsonSubTypes.Type(value = QueueJoinEvent.class, name = "queueJoin"),
    @JsonSubTypes.Type(value = QueueLeaveEvent.class, name = "queueLeave")
})
@RequiredArgsConstructor
public abstract class QueueEvent extends GameManagerEvent {
    
    @Getter
    private final int modeId;

    @Getter
    private final int playerId;
}
