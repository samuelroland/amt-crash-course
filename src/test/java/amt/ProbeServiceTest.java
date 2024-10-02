package amt;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import amt.entities.Probe;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ProbeServiceTest {
    @Inject
    ProbeService probeService;

    @Test
    void testCreationProbe() {
        var probe = new Probe("https://example.com");
        probeService.createProbeIfNotExists(probe);
        var probe2 = new Probe("https://example.com");
        probeService.createProbeIfNotExists(probe2); // should not create

        var probes = probeService.listProbes();
        assertEquals(1, probes.size());
    }

    @Test
    void testComputeStatus() {
        var probe = new Probe("https://httpstat.us/200");
        probeService.createProbeIfNotExists(probe);

        var status = probeService.computeStatus(probe);
        assertTrue(status.isUp());
    }

}
