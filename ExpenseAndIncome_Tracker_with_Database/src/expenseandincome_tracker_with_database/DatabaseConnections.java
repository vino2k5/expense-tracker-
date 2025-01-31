/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expenseandincome_tracker_with_database;

/**
 *
 * @author shankari
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnections {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/expense2_db"; // Update with your database name
        String user = "root"; // Your database username
        String password = ""; // Your database password

        return DriverManager.getConnection(url, user, password);
    }
}
