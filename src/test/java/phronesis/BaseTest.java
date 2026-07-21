package phronesis;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.client.RestTestClient;

public class BaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    protected RestTestClient restTestClient;

    @BeforeAll
    public static void init() {
        Flyway flyway =
                Flyway.configure()
                        .dataSource( "jdbc:h2:mem:database-test;DB_CLOSE_DELAY=-1" , "sa" , "password" )
                        .load();

        flyway.migrate();
    }

    @BeforeEach
    public void setup() {
        jdbcTemplate.execute("DELETE FROM steam_user;");
    }
}
