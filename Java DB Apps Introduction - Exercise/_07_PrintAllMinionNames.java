import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class _07_PrintAllMinionNames {
    static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = ConnectionCreator.createConnection();

        ArrayList<String> minionNames = getMinionNames();
        iterateMinionNames(minionNames);

        connection.close();
    }

    private static void iterateMinionNames(ArrayList<String> minionNames) {
        for (int i = 0; i < minionNames.size() / 2; i++) {
            System.out.println(minionNames.get(i));
            System.out.println(minionNames.get((minionNames.size() - 1) - i));
        }
    }

    private static ArrayList<String> getMinionNames() throws SQLException {
        ArrayList<String> minionNames = new ArrayList<>();
        
        PreparedStatement getMinionNames = connection.prepareStatement
                ("SELECT `name` \n" +
                        "FROM `minions`;");

        ResultSet rs = getMinionNames.executeQuery();
        
        while (rs.next()) {
            minionNames.add(rs.getString("name"));
        }
        
        return minionNames;
    }
}
