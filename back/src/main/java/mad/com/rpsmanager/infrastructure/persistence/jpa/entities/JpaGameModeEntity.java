package mad.com.rpsmanager.infrastructure.persistence.jpa.entities;

import jakarta.persistence.Column;
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
@Table(name ="game_modes")
public class JpaGameModeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int type;

    @ManyToOne
    @JoinColumn(name = "ruleset_id", nullable = false)
    private JpaRulesetEntity ruleset;

    @Column(unique = true, nullable = false)
    private String name;
}
