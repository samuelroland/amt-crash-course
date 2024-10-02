package amt.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Probe {
    public Probe(String url) {
        this.url = url;
    }

    public Probe() {
    }

    @Id
    private String url;

    public String getUrl() {
        return url;
    }
}
