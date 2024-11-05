package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameMatchEntity;

@Mapper(uses = {PlayerMapper.class, GameModeMapper.class, RoundMapper.class})
public interface GameMatchMapper {

    GameMatchMapper INSTANCE = Mappers.getMapper(GameMatchMapper.class);
    
    GameMatch toDomain(JpaGameMatchEntity jpaGameMatchEntity);
    JpaGameMatchEntity toEntity(GameMatch gameMatch);
}
