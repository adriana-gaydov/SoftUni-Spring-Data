import java.io.IOException;
import java.sql.*;

public class _09_IncreaseAgeStoredProcedure {
    static Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        connection = ConnectionCreator.createConnection();
        int minionID = Reader.readInt();

        callStatement(minionID);
        printMinionInfo(minionID);

        connection.close();
    }

    private static void callStatement(int minionID) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("CALL `usp_get_older`(?);");
        callableStatement.setInt(1, minionID);
        callableStatement.execute();
    }

    private static void printMinionInfo(int minionID) throws SQLException {
        PreparedStatement getMinionInfo = connection.prepareStatement("SELECT `name`, `age` FROM `minions`");
        ResultSet rs = getMinionInfo.executeQuery();
        rs.next();

        System.out.printf("%s %d", rs.getString("name"), rs.getInt("age"));
    }
}
