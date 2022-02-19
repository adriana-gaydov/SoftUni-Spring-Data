import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _08_IncreaseMinionsAge {
    static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        connection = ConnectionCreator.createConnection();
        String[] minionsIDs = Reader.readStringArray("\\s+");

        updateMinions(minionsIDs);
        printMinions();

        connection.close();
    }

    private static void printMinions() throws SQLException {
        PreparedStatement minions = connection.prepareStatement("SELECT * FROM `minions`");
        ResultSet rs = minions.executeQuery();

        while (rs.next()) {
            System.out.printf("%s %d%n", rs.getString("name"), rs.getInt("age"));
        }
    }

    private static void updateMinions(String[] minionsIDs) throws SQLException {
        PreparedStatement updateMinions = connection.prepareStatement
                ("UPDATE `minions`\n" +
                        "SET `age` = `age` + 1\n" +
                        "WHERE `id` = ?;");

        for (String minionsID : minionsIDs) {
            int e = Integer.parseInt(minionsID);
            updateMinions.setInt(1, e);
            updateMinions.executeUpdate();
        }

        updateMinions = connection.prepareStatement
                ("UPDATE `minions`\n" +
                        "SET `name` = LOWER(`name`)\n" +
                        "WHERE `id` = ?;");

        for (String minionsID : minionsIDs) {
            int e = Integer.parseInt(minionsID);
            updateMinions.setInt(1, e);
            updateMinions.executeUpdate();
        }
    }
}
