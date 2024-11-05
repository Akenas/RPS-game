package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaRulesetEntity;

public interface RulesetMapper {
    
    RulesetMapper INSTANCE = Mappers.getMapper(RulesetMapper.class);

    @Mapping(source = "options", target = "rulesetOptions", qualifiedByName = "stringToOptionsList")
    BasicRuleset toDomain(JpaRulesetEntity jpaRulesetEntity);

    @Mapping(source = "rulesetOptions", target = "options", qualifiedByName = "optionsListToString")
    JpaRulesetEntity toEntity(Ruleset ruleset);

    @Named("stringToOptionsList")
    default List<RulesetOption> stringToOptionsList(String options) {
        return Arrays.stream(options.split(","))
                     .map(RulesetOption::valueOf)
                     .collect(Collectors.toList());
    }

    @Named("optionsListToString")
    default String optionsListToString(List<RulesetOption> rulesetOptions) {
        return rulesetOptions.stream()
                             .map(RulesetOption::name)
                             .collect(Collectors.joining(","));
    }
}
