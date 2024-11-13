package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.game.Round;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaRoundEntity;

@Mapper
public interface RoundMapper {

    RoundMapper INSTANCE = Mappers.getMapper(RoundMapper.class);

    @Mapping(source = "player1Pick", target = "player1Pick", qualifiedByName = "intToRulesetOption")
    @Mapping(source = "player2Pick", target = "player2Pick", qualifiedByName = "intToRulesetOption")
    @Mapping(target = "rulesetOptionsSize", expression = "java(mapRulesetOptionsSize(jpaRoundEntity))")
    Round toDomain(JpaRoundEntity jpaRoundEntity);

    @Mapping(source = "round.player1Pick", target = "player1Pick", qualifiedByName = "rulesetOptionToInt")
    @Mapping(source = "round.player2Pick", target = "player2Pick", qualifiedByName = "rulesetOptionToInt")
    JpaRoundEntity toEntity(Round round);

    default int mapRulesetOptionsSize(JpaRoundEntity jpaRoundEntity) {
        return jpaRoundEntity.getGameMatch().getMode().getRuleset().getRulesetOptions().size();
    }

    @Named("intToRulesetOption")
    default Ruleset.RulesetOption intToRulesetOption(Integer pick) {
        if(pick != null)
            return Ruleset.RulesetOption.values()[pick];
        else return null;
    }

    @Named("rulesetOptionToInt")
    default Integer rulesetOptionToInt(Ruleset.RulesetOption option) {
        if(option != null)
            return option.ordinal();
        else return null;
    }
}

