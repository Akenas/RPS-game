package mad.com.rpsmanager.infrastructure.persistence.jpa.entities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import mad.com.rpsmanager.domain.model.game.ruleset.Ruleset.RulesetOption;

@Entity
@Table(name = "rulesets")
@Data
public class JpaRulesetEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Stores the options as a comma-separated string (e.g., "ROCK,PAPER,SCISSORS").
     */
    @Column(name = "options", nullable = false)
    private String options;

    @Column(name = "rounds_to_play", nullable = false)
    private int roundsToPlay;

    /**
     * Converts the stored comma-separated string to a list of {@link RulesetOption}.
     */
    @Transient
    public List<RulesetOption> getRulesetOptions() {
        return Arrays.stream(options.split(","))
                     .map(RulesetOption::valueOf)
                     .collect(Collectors.toList());
    }

    /**
     * Allows setting the options as a list of {@link RulesetOption}.
     */
    public void setRulesetOptions(List<RulesetOption> rulesetOptions) {
        this.options = rulesetOptions.stream()
                                     .map(RulesetOption::name)
                                     .collect(Collectors.joining(","));
    }

}