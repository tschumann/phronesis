package com.wuffesan.api;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import phronesis.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureRestTestClient
public class DonatorsControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void testDonatorCheckV2() {
        restTestClient.get().uri("/donators/check/?steamid=1&v=2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("{\"steamid\":\"1\",\"type\":0,\"tier\":0,\"typename\":\"Invalid\",\"tiername\":\"Invalid\"}");
    }

    @Test
    public void testDonatorCheckV3() {
        restTestClient.get().uri("/donators/check/?steamid=1&v=3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("{\"steamid\":\"1\",\"name\":\"\",\"type\":{\"name\":\"Invalid\",\"id\":0},\"tier\":{\"name\":\"Invalid\",\"id\":0},\"special\":{\"key\":0,\"value\":\"\"}}");
    }

    @Test
    public void testDonatorCheckV5() {
        restTestClient.get().uri("/donators/check/?steamid=1&v=5")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("{\"steamid\":\"1\",\"type\":0,\"tier\":0}");
    }
}
