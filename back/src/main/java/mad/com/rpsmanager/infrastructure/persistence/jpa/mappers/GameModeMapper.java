package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.model.game.ruleset.BasicRuleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameModeEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaRulesetEntity;


@Mapper
public interface GameModeMapper {

    GameModeMapper INSTANCE = Mappers.getMapper(GameModeMapper.class);

    @Mapping(source = "type", target = "type", qualifiedByName = "intToType")
    @Mapping(source = "ruleset", target = "ruleset", qualifiedByName = "mapRuleset")
    GameMode toDomain(JpaGameModeEntity jpaGameModeEntity);

    @Mapping(source = "type", target = "type", qualifiedByName = "typeToInt")
    @Mapping(source = "ruleset", target = "ruleset", qualifiedByName = "mapToJpaRulesetEntity")
    JpaGameModeEntity toEntity(GameMode gameMode);

    @Named("intToType")
    default GameMode.TYPE intToType(int type) {
        return GameMode.TYPE.values()[type];
    }

    @Named("typeToInt")
    default int typeToInt(GameMode.TYPE type) {
        return type.ordinal();
    }

    @Named("mapRuleset")
    default Ruleset mapRuleset(JpaRulesetEntity jpaRulesetEntity) {
        List<RulesetOption> options = Arrays.stream(jpaRulesetEntity.getOptions().split(","))
                                            .map(RulesetOption::valueOf)
                                            .collect(Collectors.toList());
        return new BasicRuleset(jpaRulesetEntity.getRoundsToPlay(), options);
    }

    @Named("mapToJpaRulesetEntity")
    default JpaRulesetEntity mapToJpaRulesetEntity(Ruleset ruleset) {
        JpaRulesetEntity entity = new JpaRulesetEntity();
        entity.setOptions(ruleset.getRulesetOptions().stream()
                                  .map(RulesetOption::name)
                                  .collect(Collectors.joining(",")));
        entity.setRoundsToPlay(ruleset.getRoundsToPlay());
        return entity;
    }
}

