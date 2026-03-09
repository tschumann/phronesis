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
    public void testVictoryStats() {
        restTestClient.post().uri("/cgi-bin/VictoryStats.pl")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    public void testIkonBoard() {
        restTestClient.post().uri("/cgi-bin/ikonboard/ikonboard.cgi")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}
