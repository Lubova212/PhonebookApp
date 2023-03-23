import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

        private static Connection conn;

        static {
            try {
                String url = "jdbc:mysql://localhost:3306/new_schema";
                String user = "root";
                String password = "12345";
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static Connection getConnection() {
            return conn;
        }

        public static void closeConnection() {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
