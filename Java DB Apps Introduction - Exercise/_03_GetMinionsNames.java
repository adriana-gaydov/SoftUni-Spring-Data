import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _03_GetMinionsNames {
    static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        connection = ConnectionCreator.createConnection();
        int villainID = Reader.readInt();

        ResultSet foundVillain = findVillain(villainID);

        if (!foundVillain.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainID);
            return;
        }

        System.out.printf("Villain: %s%n", foundVillain.getString("name"));
        getMinionsInfo(villainID);

        connection.close();
    }

    private static void getMinionsInfo(int villainID) throws SQLException {
        PreparedStatement getMinionsInfo = connection.prepareStatement
                ("SELECT DISTINCT `m`.`name`, `m`.`age`\n" +
                "FROM `villains` AS `v`\n" +
                "JOIN `minions_villains` AS `mv` ON `v`.id = `mv`.`villain_id`\n" +
                "JOIN `minions` AS `m` ON `mv`.`minion_id` = `m`.`id`\n" +
                "WHERE `v`.`id` = ?;");

        getMinionsInfo.setInt(1, villainID);

        ResultSet minionsInfo = getMinionsInfo.executeQuery();

        printMinionsInfo(minionsInfo);
    }

    private static void printMinionsInfo(ResultSet minionsInfo) throws SQLException {
        for (int i = 1; minionsInfo.next(); i++) {
            System.out.printf("%d. %s %s%n", i, minionsInfo.getString("name"), minionsInfo.getString("age"));
        }
    }

    private static ResultSet findVillain(int villainID) throws SQLException {
        PreparedStatement findVillain = connection.prepareStatement
                ("SELECT `name`\n" +
                "FROM `villains`\n" +
                "WHERE `id` = ?;");

        findVillain.setInt(1, villainID);
        return findVillain.executeQuery();
    }
}
