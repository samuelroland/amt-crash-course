package amt.entities;

import java.time.Duration;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Status {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Probe probe;
    private boolean isUp;
    private Instant timestamp;
    private Duration duration;

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProbe(Probe probe) {
        this.probe = probe;
    }

    public void setUp(boolean isUp) {
        this.isUp = isUp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Probe getProbe() {
        return probe;
    }

    public boolean isUp() {
        return isUp;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Duration getDuration() {
        return duration;
    }
}
