package mad.com.rpsmanager.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;
import mad.com.rpsmanager.domain.repositories.RulesetRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaRulesetEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.mappers.RulesetMapper;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaRulesetRepository;

@RequiredArgsConstructor
public class RulesetRepositoryImpl implements RulesetRepository{
    
    private final JpaRulesetRepository jpaRulesetRepository;

    @Override
    @SuppressWarnings("unchecked")
    public List<Ruleset> findAll() {
        
        return (List<Ruleset>) jpaRulesetRepository.findAll().stream().map(RulesetMapper.INSTANCE::toDomain);
    }

    @Override
    public Optional<Ruleset> findById(int id) {
        return jpaRulesetRepository.findById(id).map(RulesetMapper.INSTANCE::toDomain);
    }

    @Override
    public Ruleset save(Ruleset ruleset) {
        JpaRulesetEntity rulesetEntity = RulesetMapper.INSTANCE.toEntity(ruleset);
        JpaRulesetEntity savedEntity = jpaRulesetRepository.save(rulesetEntity);
        return RulesetMapper.INSTANCE.toDomain(savedEntity);
    }
}
