package mad.com.rpsmanager.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.GameMatch;
import mad.com.rpsmanager.domain.repositories.GameMatchRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaGameMatchEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.mappers.GameMatchMapper;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaGameMatchRepository;

@RequiredArgsConstructor
public class GameMatchRepositoryImpl implements GameMatchRepository{

    private final JpaGameMatchRepository jpaGameMatchRepository;

    @Override
    public Optional<GameMatch> findById(String id) {
         return jpaGameMatchRepository.findById(id).map(GameMatchMapper.INSTANCE::toDomain);
    }

    @Override
    public List<GameMatch> findByPlayer1IdOrPlayer2Id(Long id) {
        return jpaGameMatchRepository.findByPlayer1IdOrPlayer2Id(id)
            .stream()
            .map(GameMatchMapper.INSTANCE::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<GameMatch> findByModeId(int id) {
        return jpaGameMatchRepository.findByModeId(id)
            .stream()
            .map(GameMatchMapper.INSTANCE::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<GameMatch> findByOngoingTrue() {
        return jpaGameMatchRepository.findByOngoingTrue()
            .stream()
            .map(GameMatchMapper.INSTANCE::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public GameMatch save(GameMatch match) {
        JpaGameMatchEntity matchEntity = GameMatchMapper.INSTANCE.toEntity(match);
        JpaGameMatchEntity savedEntity = jpaGameMatchRepository.save(matchEntity);
        return GameMatchMapper.INSTANCE.toDomain(savedEntity);
    }
    
}
