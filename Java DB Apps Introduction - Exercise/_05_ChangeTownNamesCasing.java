import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class _05_ChangeTownNamesCasing {
    static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        connection = ConnectionCreator.createConnection();
        String countryName = Reader.readString();

        int updatedTownNames = updateTowns(countryName);

        if (updatedTownNames == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.printf("%d town names were affected.%n", updatedTownNames);

        printAffectedTowns(countryName);

        connection.close();
    }

    private static void printAffectedTowns(String countryName) throws SQLException {
        PreparedStatement getTownNames = connection.prepareStatement
                ("SELECT `name`\n" +
                        "FROM `towns`\n" +
                        "WHERE `country` = ?;");

        getTownNames.setString(1, countryName);

        ResultSet townNames = getTownNames.executeQuery();
        ArrayList<String> townNamesList = getList(townNames);

        System.out.println(townNamesList);

    }

    private static ArrayList<String> getList(ResultSet townNames) throws SQLException {
        ArrayList<String> result = new ArrayList<>();

        while (townNames.next()) {
            result.add(townNames.getString("name"));
        }

        return result;
    }

    private static int updateTowns(String countryName) throws SQLException {
        PreparedStatement updateTowns = connection.prepareStatement
                ("UPDATE `towns`\n" +
                        "SET `name` = UPPER(`name`)\n" +
                        "WHERE `country` = ?;");

        updateTowns.setString(1, countryName);
        return updateTowns.executeUpdate();
    }
}
