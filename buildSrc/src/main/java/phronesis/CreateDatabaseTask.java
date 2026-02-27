package phronesis;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.h2.Driver;

/**
 * Create a H2 database - do this in a Gradle plugin because doing it in build.gradle doesn't work (Gradle can't see H2 in its classpath?).
 * This seems to spew out of memory exceptions even though it works.
 */
public class CreateDatabaseTask extends DefaultTask {

    @Input
    public String path;

    public String getPath() {
        return path;
    }

    @TaskAction
    public void createDatabase() throws SQLException {
        DriverManager.setLogWriter(new PrintWriter(System.out, true));
        DriverManager.registerDriver(new Driver());

        // to create a H2 database, connect to it
        final Connection connection = DriverManager.getConnection("jdbc:h2:" + getPath() +  "/database", "phronesis", "password");
        connection.close();
    }
}
