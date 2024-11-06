package mad.com.rpsmanager.domain.repositories;

import java.util.List;
import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.players.Player;

public interface PlayerRepository {
    
    List<Player> findAll();
    Optional<Player> findById(long id);
    Optional<Player> findByAlias(String name);
    List<Player> findByConnectedTrue();
    Player save(Player player);
}
