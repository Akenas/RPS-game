package mad.com.rpsmanager.infrastructure.persistence.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaUserEntity;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDomain(JpaUserEntity userEntity);
    JpaUserEntity toEntity(User user);
}
