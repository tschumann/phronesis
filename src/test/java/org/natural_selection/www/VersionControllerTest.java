package org.natural_selection.www;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import phronesis.Main;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class VersionControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void testGetGameVersionStringNoAuth() {
        restTestClient.get().uri("/auth.txt")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");

        restTestClient.get().uri("/auth/version.txt")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");
    }

    @Test
    public void testGetGameVersionStringIncorrectAuth() {
        restTestClient.get().uri("/auth.txt")
                .header("Authorization", "Basic password")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");

        restTestClient.get().uri("/auth/version.txt")
                .header("Authorization", "Basic password")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");
    }

    @Test
    public void testGetGameVersionString() {
        restTestClient.get().uri("/auth.txt")
                .header("Authorization", "Basic YXV0aDptbmJ2NXRnYg==")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("v3.2.0");

        restTestClient.get().uri("/auth/version.txt")
                .header("Authorization", "Basic YXV0aDptbmJ2NXRnYg==")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("v3.2.0");
    }
}
