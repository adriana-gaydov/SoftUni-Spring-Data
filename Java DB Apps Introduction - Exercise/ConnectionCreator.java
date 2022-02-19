import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final String currentURL = "jdbc:mysql://localhost:3307/minions_db?useSSL=false";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(currentURL, username, password);
    }
}
