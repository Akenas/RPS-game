package mad.com.rpsmanager.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name ="game_rounds")
public class JpaRoundEntity {
    
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean completed;
    private int winner;
    private int player1Pick;
    private int player2Pick;

    @ManyToOne
    @JoinColumn(name = "game_match_id")
    private JpaGameMatchEntity gameMatch;
}
