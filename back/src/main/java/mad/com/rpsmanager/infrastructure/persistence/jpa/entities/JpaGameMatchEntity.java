package mad.com.rpsmanager.infrastructure.persistence.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "game_matches")
@Data
public class JpaGameMatchEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private JpaPlayerEntity player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private JpaPlayerEntity player2;

    @ManyToOne
    @JoinColumn(name = "game_mode_id")
    private JpaGameModeEntity mode;

    private int winner;

    @OneToMany(mappedBy = "gameMatch", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<JpaRoundEntity> rounds = new ArrayList<>();

    private boolean ongoing;
}
