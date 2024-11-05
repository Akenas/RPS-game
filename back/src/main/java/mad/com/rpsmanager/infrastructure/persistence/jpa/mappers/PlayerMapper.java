package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.game.players.BasicPlayer;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaPlayerEntity;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    BasicPlayer toDomain(JpaPlayerEntity jpaPlayerEntity);
    JpaPlayerEntity toEntity(BasicPlayer player);
}
