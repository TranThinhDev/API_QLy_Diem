package util;

import java.sql.*;

public class DbUtil {

    // Đóng Connection, Statement, ResultSet an toàn
    public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }

    // Đóng Connection, Statement
    public static void closeQuietly(Connection conn, Statement stmt) {
        closeQuietly(conn, stmt, null);
    }

    // Đóng Connection
    public static void closeQuietly(Connection conn) {
        closeQuietly(conn, null, null);
    }
}
