package mad.com.rpsmanager.domain.transients.events.game;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import mad.com.rpsmanager.domain.transients.events.GameManagerEvent;
import mad.com.rpsmanager.service.game.events.GameServiceVisitor;

public class GameForfeitEvent extends GameManagerEvent {

    @Getter
    private final String matchId;
    @Getter
    private final long playerId;


    @JsonCreator
    public GameForfeitEvent(@JsonProperty("matchId") String matchId, @JsonProperty("playerId") long playerId){
       this.matchId = matchId;
       this.playerId = playerId;
    }
   
    @Override
    public void accept(GameServiceVisitor visitor) throws IOException {
        visitor.visit(this);
    }
}