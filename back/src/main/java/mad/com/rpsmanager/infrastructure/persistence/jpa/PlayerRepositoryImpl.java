package mad.com.rpsmanager.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import mad.com.rpsmanager.domain.model.game.players.Player;
import mad.com.rpsmanager.domain.repositories.PlayerRepository;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaPlayerEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.mappers.PlayerMapper;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaPlayerRepository;


@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository{

    private final JpaPlayerRepository playerRepository;
    
    @Override
    public List<Player> findAll() {
        return playerRepository.findAll().stream().map(PlayerMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Player> findById(long id) {
        return playerRepository.findById(id).map(PlayerMapper.INSTANCE::toDomain);
    }

    @Override
    public Optional<Player> findByAlias(String name) {
        return playerRepository.findByAlias(name).map(PlayerMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Player> findByConnectedTrue() {
        return playerRepository.findByConnectedTrue().stream().map(PlayerMapper.INSTANCE::toDomain).collect(Collectors.toList());
    }

    @Override
    public Player save(Player player) {
        JpaPlayerEntity playerEntity = PlayerMapper.INSTANCE.toEntity(player);
        JpaPlayerEntity savedEntity = playerRepository.save(playerEntity);
        return PlayerMapper.INSTANCE.toDomain(savedEntity);
    }
    
}
