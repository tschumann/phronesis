package phronesis;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;
import org.h2.Driver;

/**
 * Create a H2 database.
 * This seems to spew out of memory exceptions even though it works.
 */
public class CreateDatabaseTask extends DefaultTask {

    @Internal
    public String getDatabasePath() {
        return "jdbc:h2:" + getProject().getProjectDir().getAbsolutePath() + "/";
    }

    @TaskAction
    public void createDatabase() throws SQLException {
        DriverManager.setLogWriter(new PrintWriter(System.out, true));
        DriverManager.registerDriver(new Driver());

        // to create a H2 database, connect to it
        final Connection connection = DriverManager.getConnection(getDatabasePath() + "database", "sa", "password");
        connection.close();
    }
}
