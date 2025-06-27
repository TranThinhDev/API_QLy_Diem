/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConnectDatabase.Conn;
import model.TaiKhoan;
import java.sql.*;

public class LoginDAO {

    public static TaiKhoan checkLogin(String tenDN, String matKhau) {
        String sql = "SELECT * FROM taikhoan WHERE tenDN = ? AND matKhau = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenDN);
            stmt.setString(2, matKhau);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getString("tenDN"),
                        rs.getString("matKhau"),
                        rs.getString("hoTen"),
                        rs.getString("quyen")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // đăng nhập thất bại
    }
}

