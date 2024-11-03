package mad.com.rpsmanager.domain.transients.events.game;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import mad.com.rpsmanager.domain.transients.events.GameManagerEvent;
import mad.com.rpsmanager.service.game.events.GameServiceVisitor;

public class GameMatchPickEvent extends GameManagerEvent {

    @Getter
    private final String matchId;
    @Getter
    private final int playerId;
    @Getter
    private final int pick;

    @JsonCreator
    public GameMatchPickEvent(@JsonProperty("matchId") String matchId, @JsonProperty("playerId") int playerId,@JsonProperty("pick") int pick){
       this.matchId = matchId;
       this.playerId = playerId;
       this.pick = pick;
    }
   
    @Override
    public void accept(GameServiceVisitor visitor) throws IOException {
        visitor.visit(this);
    }
}
