package dao;

import ConnectDatabase.Conn;
import model.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    // Lấy toàn bộ tài khoản
    public static List<TaiKhoan> getAll() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";

        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                        rs.getString("tenDN"),
                        rs.getString("matKhau"),
                        rs.getString("hoTen"),
                        rs.getString("quyen")
                );
                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy theo tên đăng nhập
    public static TaiKhoan getByUsername(String tenDN) {
        String sql = "SELECT * FROM taikhoan WHERE tenDN = ?";

        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenDN);
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

        return null;
    }

    // Thêm mới
    public static boolean insert(TaiKhoan tk) {
        String sql = "INSERT INTO taikhoan (tenDN, matKhau, hoTen, quyen) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tk.getTenDN());
            stmt.setString(2, tk.getMatKhau());
            stmt.setString(3, tk.getHoTen());
            stmt.setString(4, tk.getQuyen());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật
    public static boolean update(TaiKhoan tk) {
        String sql = "UPDATE taikhoan SET matKhau = ?, hoTen = ?, quyen = ? WHERE tenDN = ?";

        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tk.getMatKhau());
            stmt.setString(2, tk.getHoTen());
            stmt.setString(3, tk.getQuyen());
            stmt.setString(4, tk.getTenDN());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xoá tài khoản
    public static boolean delete(String tenDN) {
        String sql = "DELETE FROM taikhoan WHERE tenDN = ?";

        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenDN);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
