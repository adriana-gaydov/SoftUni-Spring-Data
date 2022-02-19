import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _02_GetVillainsNames {
    static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = ConnectionCreator.createConnection();
        PreparedStatement preparedStatement = getPreparedStatement();
        ResultSet resultSet = preparedStatement.executeQuery();
        printResult(resultSet);
        connection.close();
    }

    private static void printResult(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.printf("%s %s%n", resultSet.getString("name"), resultSet.getString("minions_count"));
        }
    }

    private static PreparedStatement getPreparedStatement() throws SQLException {
        return connection.prepareStatement
                ("SELECT `name`, COUNT(DISTINCT  `minion_id`) as `minions_count`\n" +
                        "FROM `villains` as `v`\n" +
                        "JOIN  `minions_villains` AS `mv` ON `v`.`id` = `mv`.`villain_id`\n" +
                        "GROUP BY `v`.`id`\n" +
                        "HAVING  `minions_count` > 15\n" +
                        "ORDER BY `minions_count` DESC;");
    }
}
