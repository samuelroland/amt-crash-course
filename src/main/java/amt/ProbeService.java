package amt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import amt.entities.Probe;
import amt.entities.Status;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProbeService {

    @Inject
    EntityManager em;

    @Transactional
    public Probe createProbeIfNotExists(Probe probe) {
        if (em.find(Probe.class, probe.getUrl()) == null) {
            em.persist(probe);
        }
        return probe;
    }

    public List<Probe> listProbes() {
        return em.createQuery("select p from Probe p", Probe.class).getResultList();
    }

    @Transactional
    public Status computeStatus(Probe probe) {
        var status = new Status();
        status.setProbe(probe);
        var start = Instant.now();
        status.setTimestamp(start); // arbitrary choice

        // HTTP request to URL
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(probe.getUrl()))
                .GET()
                .build();

        HttpResponse<Void> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.discarding());
            status.setUp(response.statusCode() == 200);
        } catch (IOException e) {
            status.setUp(false);
        } catch (InterruptedException e) {
            status.setUp(false);
        }

        var stop = Instant.now();
        status.setDuration(Duration.between(start, stop));
        em.persist(status);
        return status;
    }

}
