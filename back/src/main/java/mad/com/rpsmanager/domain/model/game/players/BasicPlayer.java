package mad.com.rpsmanager.domain.model.game.players;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Basic implementation of the {@link Player} interface.
 * This class represents a player with a fixed ID, alias, and an initial rating of zero.
 * The rating calculation is not implemented, as {@code BasicPlayer} does not have rating updates.
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class BasicPlayer implements Player {

    /**
     * Unique identifier of the player.
     */
    @Getter
    private final int id;

    /**
     * Alias or nickname of the player.
     */
    @Getter
    private final String alias;

     /**
     * The current rating of the player. Defaults to zero for {@code BasicPlayer}.
     */
    @Getter
    private int rating = 0;

    /**
     * Computes and updates the player's rating.
     * In this basic implementation, the rating remains unchanged.
     */
    @Override
    public void computeRating() {
        //Basic player has no rating
    }
    
    
}
