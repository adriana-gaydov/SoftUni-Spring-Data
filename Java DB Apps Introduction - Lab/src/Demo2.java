import java.util.Scanner;
import java.sql.*;

public class Demo2 {
        public static void main(String[] args) throws SQLException {
                Scanner sc = new Scanner(System.in);

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/diablo?useSSL=false", "root", "root");

                PreparedStatement ps = connection.prepareStatement("SELECT user_name, first_name, last_name, COUNT(*) AS games_count\n" +
                        "FROM users\n" +
                        "JOIN users_games ON users.id = users_games.user_id\n" +
                        "JOIN games ON users_games.game_id = games.id\n" +
                        "WHERE user_name = ?\n" +
                        "GROUP BY users.id;\n");

                ps.setString(1, sc.nextLine());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                        System.out.printf("User: %s%n", rs.getString("user_name"));
                        System.out.printf("%s %s has played %d games", rs.getString("first_name"), rs.getString("last_name"),
                                rs.getInt("games_count"));
                } else {
                        System.out.println("No such user exists");
                }

                connection.close();
        }
}
