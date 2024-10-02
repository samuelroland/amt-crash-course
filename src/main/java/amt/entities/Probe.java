package amt.entities;

import java.net.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Probe {
    @Id
    private URL url;
}
