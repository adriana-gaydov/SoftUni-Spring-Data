import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _06_RemoveVillain {
    static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        connection = ConnectionCreator.createConnection();
        int villainID = Reader.readInt();

        if (!checkVillainExists(villainID)) {
            System.out.println("No such villain was found");
            return;
        }

        try {
            connection.setAutoCommit(false);

            int releasedMinionsCount = deleteFromMappingTable(villainID);
            String villainName = getVillainName(villainID);
            deleteFromVillainsTable(villainID);

            connection.commit();

            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released", releasedMinionsCount);
        } catch (SQLException e) {
            connection.rollback();
        }

        connection.close();
    }

    private static String getVillainName(int villainID) throws SQLException {
        PreparedStatement findVillain = connection.prepareStatement
                ("SELECT `name` FROM `villains`\n" +
                        "WHERE `id` = ?;");

        findVillain.setInt(1, villainID);

        ResultSet foundVillain = findVillain.executeQuery();
        foundVillain.next();

        return foundVillain.getString("name");
    }

    private static void deleteFromVillainsTable(int villainID) throws SQLException {
        PreparedStatement deleteVillain = connection.prepareStatement
                ("DELETE FROM `villains`\n" +
                        "WHERE `id` = ?;");

        deleteVillain.setInt(1, villainID);

        deleteVillain.executeUpdate();
    }

    private static int deleteFromMappingTable(int villainID) throws SQLException {
        PreparedStatement deleteVillain = connection.prepareStatement
                ("DELETE FROM `minions_villains`\n" +
                        "WHERE `villain_id` = ?;");

        deleteVillain.setInt(1, villainID);

        return deleteVillain.executeUpdate();
    }

    private static boolean checkVillainExists(int villainID) throws SQLException {
        PreparedStatement findVillain = connection.prepareStatement
                ("SELECT `name`\n" +
                        "FROM `villains`\n" +
                        "WHERE `id` = ?;");

        findVillain.setInt(1, villainID);

        ResultSet rs = findVillain.executeQuery();
        return rs.next();
    }
}
