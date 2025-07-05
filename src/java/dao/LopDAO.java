package dao;

import ConnectDatabase.Conn;
import model.Lop;

import java.sql.*;
import java.util.*;

public class LopDAO {

    public static List<Lop> getAll() {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM lop";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Lop(
                    rs.getString("maLop"),
                    rs.getString("tenLop"),
                    rs.getString("maNganh"),
                    rs.getString("maGV")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Lop getById(String maLop) {
        String sql = "SELECT * FROM lop WHERE maLop = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLop);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Lop(
                    rs.getString("maLop"),
                    rs.getString("tenLop"),
                    rs.getString("maNganh"),
                    rs.getString("maGV")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Lop> search(String keyword) {
        List<Lop> list = new ArrayList<>();
        String sql = "SELECT * FROM lop WHERE maLop LIKE ? OR tenLop LIKE ? OR maGV LIKE ? OR maNganh LIKE ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            stmt.setString(1, kw);
            stmt.setString(2, kw);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Lop(
                    rs.getString("maLop"),
                    rs.getString("tenLop"),
                    rs.getString("maNganh"),
                    rs.getString("maGV")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insert(Lop lop) {
        String sql = "INSERT INTO lop(maLop, tenLop, maNganh, maGV) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lop.getMaLop());
            stmt.setString(2, lop.getTenLop());
            stmt.setString(3, lop.getMaNganh());
            stmt.setString(4, lop.getMaGV());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(Lop lop) {
        String sql = "UPDATE lop SET tenLop=?, maNganh=?, maGV=? WHERE maLop=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lop.getTenLop());
            stmt.setString(2, lop.getMaNganh());
            stmt.setString(3, lop.getMaGV());
            stmt.setString(4, lop.getMaLop());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String maLop) {
        String sql = "DELETE FROM lop WHERE maLop=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLop);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
