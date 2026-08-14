package com.teamfortress.www;

import org.junit.jupiter.api.Test;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import phronesis.BaseTest;
import phronesis.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureRestTestClient
@AutoConfigureMockMvc
public class GetInventoryControllerTest extends BaseTest {

    @Test
    public void testGetInventoryNoTicket() {
        restTestClient.get().uri("/ISDK/GetInventory/v0001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("{\n    \"result\": 8,\n    \"error\": \"no msg specified\"\n}");
    }

    @Test
    public void testGetInventory() {
        restTestClient.get().uri("/ISDK/GetInventory/v0001?ticket=abcdef")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("{}");
    }
}
