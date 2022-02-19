import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _04_AddMinion {
    static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        connection = ConnectionCreator.createConnection();

        String[] minionTokens = Reader.readStringArray("\\s+");
        String[] villainTokens = Reader.readStringArray("\\s+");

        checkTownName(minionTokens);
        checkVillain(villainTokens);

        if (insertMinion(minionTokens)) {
            addMinionToVillain(minionTokens, villainTokens);
        }

        connection.close();
    }

    private static void addMinionToVillain(String[] minionTokens, String[] villainTokens) throws SQLException {
        String minionName = minionTokens[1];
        int minionAge = Integer.parseInt(minionTokens[2]);
        String villainName = villainTokens[1];

        int minionID = getMinionID(minionName, minionAge);
        int villainID = getVillainID(villainName);

        if(insertMinionVillain(minionID, villainID)) {
            System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
        }
    }

    private static boolean insertMinionVillain(int minionID, int villainID) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement
                ("INSERT INTO `minions_villains`\n" +
                        "VALUES (?, ?);");

        insertStatement.setInt(1, minionID);
        insertStatement.setInt(2, villainID);

        return insertStatement.executeUpdate() >= 1;
    }

    private static int getVillainID(String villainName) throws SQLException {
        PreparedStatement findVillainID = connection.prepareStatement
                ("SELECT `id`\n" +
                        "FROM `villains`\n" +
                        "WHERE `name` = ?;");

        findVillainID.setString(1, villainName);

        ResultSet foundVillainID = findVillainID.executeQuery();
        foundVillainID.next();

        return foundVillainID.getInt("id");
    }

    private static int getMinionID(String minionName, int minionAge) throws SQLException {
        PreparedStatement findMinionID = connection.prepareStatement
                ("SELECT `id`\n" +
                        "FROM `minions`\n" +
                        "WHERE `name` = ? AND age = ?;");

        findMinionID.setString(1, minionName);
        findMinionID.setInt(2, minionAge);

        ResultSet foundMinionID = findMinionID.executeQuery();
        foundMinionID.next();

        return foundMinionID.getInt("id");
    }

    private static boolean insertMinion(String[] minionTokens) throws SQLException {
        String minionName = minionTokens[1];
        int minionAge = Integer.parseInt(minionTokens[2]);
        String minionTown = minionTokens[3];
        int minionTownID = getTownID(minionTown);

        PreparedStatement insertMinion = connection.prepareStatement
                ("INSERT INTO `minions`\n" +
                        "(`name`, `age`, `town_id`)\n" +
                        "VALUES (?, ?, ?);");

        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, minionTownID);

        return insertMinion.executeUpdate() >= 1;
    }

    private static int getTownID(String townName) throws SQLException {
        PreparedStatement findTownID = connection.prepareStatement
                ("SELECT `id` \n" +
                        "FROM `towns`\n" +
                        "WHERE `name` = ?;");

        findTownID.setString(1, townName);
        ResultSet foundTownID = findTownID.executeQuery();
        foundTownID.next();

        return foundTownID.getInt("id");
    }

    private static void checkVillain(String[] villainTokens) throws SQLException {
        String villainName = villainTokens[1];

        PreparedStatement findVillain = connection.prepareStatement
                ("SELECT * \n" +
                        "FROM `villains` \n" +
                        "WHERE `name` = ?;");

        findVillain.setString(1, villainName);
        ResultSet foundVillain = findVillain.executeQuery();

        if (!foundVillain.next()) {
            insertVillain(villainName);
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }
    }

    private static boolean insertVillain(String villainName) throws SQLException {
        PreparedStatement insertVillain = connection.prepareStatement
                ("INSERT INTO `villains`\n" +
                        "(`name`, `evilness_factor`)\n" +
                        "VALUES (?, 'evil');");

        insertVillain.setString(1, villainName);
        return insertVillain.executeUpdate() >= 1;
    }

    private static void checkTownName(String[] minionTokens) throws SQLException {
        String townName = minionTokens[3];

        PreparedStatement findTown = connection.prepareStatement
                ("SELECT * \n" +
                        "FROM `towns` \n" +
                        "WHERE `name` = ?;");

        findTown.setString(1, townName);
        ResultSet foundTown = findTown.executeQuery();

        if (!foundTown.next()) {
            insertTown(townName);
            System.out.printf("Town %s was added to the database.%n", townName);
        }
    }

    private static boolean insertTown(String townName) throws SQLException {
        PreparedStatement insertTown = connection.prepareStatement
                ("INSERT INTO `towns`\n" +
                        "(`name`)\n" +
                        "VALUES (?);");

        insertTown.setString(1, townName);
        return insertTown.executeUpdate() >= 1;
    }
}
