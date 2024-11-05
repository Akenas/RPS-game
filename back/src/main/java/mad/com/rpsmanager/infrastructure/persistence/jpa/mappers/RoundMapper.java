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
    @Mapping(source = "jpaRoundEntity.gameMatch.mode.ruleset.roundsToPlay", target = "rulesetOptionsSize")
    Round toDomain(JpaRoundEntity jpaRoundEntity);

    @Mapping(source = "round.player1Pick", target = "player1Pick", qualifiedByName = "rulesetOptionToInt")
    @Mapping(source = "round.player2Pick", target = "player2Pick", qualifiedByName = "rulesetOptionToInt")
    JpaRoundEntity toEntity(Round round);

    @Named("intToRulesetOption")
    default Ruleset.RulesetOption intToRulesetOption(int pick) {
        return Ruleset.RulesetOption.values()[pick];
    }

    @Named("rulesetOptionToInt")
    default int rulesetOptionToInt(Ruleset.RulesetOption option) {
        return option.ordinal();
    }
}

