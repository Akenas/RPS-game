package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameModeEntity;


@Mapper(uses = RulesetMapper.class)
public interface GameModeMapper {

    GameModeMapper INSTANCE = Mappers.getMapper(GameModeMapper.class);

    @Mapping(source = "type", target = "type", qualifiedByName = "intToType")
    GameMode toDomain(JpaGameModeEntity jpaGameModeEntity);

    @Mapping(source = "type", target = "type", qualifiedByName = "typeToInt")
    JpaGameModeEntity toEntity(GameMode gameMode);

    @Named("intToType")
    default GameMode.TYPE intToType(int type) {
        return GameMode.TYPE.values()[type];
    }

    @Named("typeToInt")
    default int typeToInt(GameMode.TYPE type) {
        return type.ordinal();
    }
}

