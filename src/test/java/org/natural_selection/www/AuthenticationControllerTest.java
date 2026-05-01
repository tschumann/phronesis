package org.natural_selection.www;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import phronesis.Main;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class AuthenticationControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void testAuthenticationNoAuth() {
        restTestClient.get().uri("/auth.php")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");

        restTestClient.get().uri("/auth/")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");
    }

    @Test
    public void testAuthenticationIncorrectAuth() {
        restTestClient.get().uri("/auth.php")
                .header("Authorization", "Basic password")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");

        restTestClient.get().uri("/auth/")
                .header("Authorization", "Basic password")
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(String.class).isEqualTo("Bad credentials");
    }

    @Test
    public void testAuthentication() {
        restTestClient.get().uri("/auth.php")
                .header("Authorization", "Basic YXV0aDptbmJ2NXRnYg==")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("dev 123 STEAM_0:0:11101\nguide 125 STEAM_0:0:11102\n");

        restTestClient.get().uri("/auth/")
                .header("Authorization", "Basic YXV0aDptbmJ2NXRnYg==")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("dev 123 STEAM_0:0:11101\nguide 125 STEAM_0:0:11102\n");
    }
}
