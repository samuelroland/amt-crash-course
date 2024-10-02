package amt.entities;

import java.time.Duration;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Status {
    @Id
    private Long id;
    @ManyToOne
    private Probe probe;
    private boolean isUp;
    private Instant timestamp;
    private Duration duration;
}
