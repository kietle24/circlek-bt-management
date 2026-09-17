package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QLSieuThi;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASS = "123";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
