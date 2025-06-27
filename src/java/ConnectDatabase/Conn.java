/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDatabase;

/**
 *
 * @author dell
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conn {
    private static final String URL = "jdbc:mysql://localhost:3306/qly_diem_sv?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // thay đổi nếu cần
    private static final String PASS = "";     // thay đổi nếu cần

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Đảm bảo driver được load
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}


