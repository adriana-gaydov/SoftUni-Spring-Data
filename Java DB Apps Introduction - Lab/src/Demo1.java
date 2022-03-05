import java.sql.*;
import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/soft_uni?useSSL=false", "root", "root");

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        ps.setDouble(1, Double.parseDouble(sc.nextLine()));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("%s %s%n", rs.getString("first_name"), rs.getString("last_name"));
        }

        connection.close();

    }
}
