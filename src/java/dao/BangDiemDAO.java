package dao;

import ConnectDatabase.Conn;
import model.BangDiem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BangDiemDAO {

    public static List<BangDiem> getAll() {
        List<BangDiem> list = new ArrayList<>();
        String sql = "SELECT * FROM bangdiem";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new BangDiem(
                        rs.getString("maSV"),
                        rs.getInt("tongSoTC"),
                        rs.getDouble("diemHe10"),
                        rs.getDouble("diemHe4")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static BangDiem getById(String maSV) {
        String sql = "SELECT * FROM bangdiem WHERE maSV = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BangDiem(
                        rs.getString("maSV"),
                        rs.getInt("tongSoTC"),
                        rs.getDouble("diemHe10"),
                        rs.getDouble("diemHe4")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insert(BangDiem bd) {
        String sql = "INSERT INTO bangdiem(maSV, tongSoTC, diemHe10, diemHe4) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bd.getMaSV());
            stmt.setInt(2, bd.getTongSoTC());
            stmt.setDouble(3, bd.getDiemHe10());
            stmt.setDouble(4, bd.getDiemHe4());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(BangDiem bd) {
        String sql = "UPDATE bangdiem SET tongSoTC=?, diemHe10=?, diemHe4=? WHERE maSV=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bd.getTongSoTC());
            stmt.setDouble(2, bd.getDiemHe10());
            stmt.setDouble(3, bd.getDiemHe4());
            stmt.setString(4, bd.getMaSV());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String maSV) {
        String sql = "DELETE FROM bangdiem WHERE maSV=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
