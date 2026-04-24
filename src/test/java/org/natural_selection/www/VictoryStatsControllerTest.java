package org.natural_selection.www;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import phronesis.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureRestTestClient
public class VictoryStatsControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void testVictoryStatsMissing() {
        restTestClient.post().uri("/cgi-bin/VictoryStats.pl")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class).isEqualTo("Invalid format");

        restTestClient.post().uri("/cgi-bin/ikonboard/ikonboard.cgi")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class).isEqualTo("Invalid format");
    }

    @Test
    public void testVictoryStatsInvalid() {
        restTestClient.post().uri("/cgi-bin/VictoryStats.pl")
                .body("a")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class).isEqualTo("Invalid format");

        restTestClient.post().uri("/cgi-bin/ikonboard/ikonboard.cgi")
                .body("a")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class).isEqualTo("Invalid format");
    }

    @Test
    public void testVictoryStatsSuccess() {
        restTestClient.post().uri("/cgi-bin/VictoryStats.pl")
                .body("1?aliens?32?32?ns_eclipse?v3.2.0?60?0")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        restTestClient.post().uri("/cgi-bin/ikonboard/ikonboard.cgi")
                .body("1?aliens?32?32?ns_eclipse?v3.2.0?60?0")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}
