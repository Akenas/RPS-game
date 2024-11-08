package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameMatchEntity;

@Mapper(uses = {PlayerMapper.class, GameModeMapper.class, RoundMapper.class})
public interface GameMatchMapper {

    GameMatchMapper INSTANCE = Mappers.getMapper(GameMatchMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "player1", target = "player1")
    @Mapping(source = "player2", target = "player2")
    @Mapping(source = "mode", target = "mode")
    GameMatch toDomain(JpaGameMatchEntity jpaGameMatchEntity);
    JpaGameMatchEntity toEntity(GameMatch gameMatch);
}
