package phronesis;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void cleanup() {
        jdbcTemplate.execute("DELETE FROM steam_user;");
    }
}
