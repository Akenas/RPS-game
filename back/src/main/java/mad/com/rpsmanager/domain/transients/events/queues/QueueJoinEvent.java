package mad.com.rpsmanager.domain.transients.events.queues;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import mad.com.rpsmanager.domain.transients.events.GameManagerEvent;
import mad.com.rpsmanager.service.game.events.GameServiceVisitor;

public class QueueJoinEvent extends GameManagerEvent {

    @Getter
    private final int modeId;
    @Getter
    private final long playerId;

    @JsonCreator
    public QueueJoinEvent(@JsonProperty("modeId") int modeId, @JsonProperty("playerId") long playerId){
       this.modeId = modeId;
       this.playerId = playerId;
    }
   
    @Override
    public void accept(GameServiceVisitor visitor) throws IOException {
        visitor.visit(this);
    }
}
