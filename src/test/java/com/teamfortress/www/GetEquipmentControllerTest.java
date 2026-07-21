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
public class GetEquipmentControllerTest extends BaseTest {

    @Test
    public void testGetEquipment() {
        restTestClient.get().uri("/ISDK/GetEquipment/v0001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("{}");
    }
}
