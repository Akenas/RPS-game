package mad.com.rpsmanager.domain.repositories;

import java.util.List;
import java.util.Optional;

import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;

public interface RulesetRepository {
    
    List<Ruleset> findAll();
    Optional<Ruleset> findById(int id);
    Ruleset save(Ruleset ruleset);
}
