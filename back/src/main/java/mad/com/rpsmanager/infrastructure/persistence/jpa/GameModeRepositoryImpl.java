package mad.com.rpsmanager.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.GameMode;
import mad.com.rpsmanager.domain.repositories.GameModeRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameModeEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.mappers.GameModeMapper;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaGameModeRepository;

@RequiredArgsConstructor
public class GameModeRepositoryImpl implements GameModeRepository{

    private final JpaGameModeRepository jpaGameModeRepository;
    @Override
    public List<GameMode> findAll() {
       
        return jpaGameModeRepository.findAll().stream().map(GameModeMapper.INSTANCE::toDomain).toList();
    }

    @Override
    public Optional<GameMode> findById(int id) {
       return jpaGameModeRepository.findById(id).map(GameModeMapper.INSTANCE::toDomain);
    }

    @Override
    public Optional<GameMode> findByName(String name) {
        return jpaGameModeRepository.findByName(name).map(GameModeMapper.INSTANCE::toDomain);
    }

    @Override
    public List<GameMode> findByType(int type) {
        return jpaGameModeRepository.findByType(type).stream().map(GameModeMapper.INSTANCE::toDomain).toList();
    }

    @Override
    public GameMode save(GameMode gameMode) {
        JpaGameModeEntity modeEntity = GameModeMapper.INSTANCE.toEntity(gameMode);
        JpaGameModeEntity savedEntity = jpaGameModeRepository.save(modeEntity);
        return GameModeMapper.INSTANCE.toDomain(savedEntity);
    }
    
}
