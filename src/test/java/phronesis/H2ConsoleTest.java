package phronesis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true"})
@AutoConfigureRestTestClient
@AutoConfigureMockMvc
public class H2ConsoleTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void testGetH2Console() {
        restTestClient.get().uri("/h2-console/")
                .exchange()
                .expectStatus().isOk();
    }
}
