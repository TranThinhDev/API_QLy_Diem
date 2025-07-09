package dao;

import ConnectDatabase.Conn;
import model.LopHP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LopHPDAO {

    public static List<LopHP> getAll() {
        List<LopHP> list = new ArrayList<>();
        String sql = "SELECT * FROM lophp";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new LopHP(
                    rs.getString("maHP"),
                    rs.getString("tenHP"),
                    rs.getString("maLop"),
                    rs.getString("maMon"),
                    rs.getString("maGV")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static LopHP getById(String maHP) {
        String sql = "SELECT * FROM lophp WHERE maHP = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maHP);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new LopHP(
                    rs.getString("maHP"),
                    rs.getString("tenHP"),
                    rs.getString("maLop"),
                    rs.getString("maMon"),
                    rs.getString("maGV")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<LopHP> search(String keyword) {
        List<LopHP> list = new ArrayList<>();
        String sql = "SELECT * FROM lophp WHERE maHP LIKE ? OR tenHP LIKE ? OR maLop LIKE ? OR maMon LIKE ? OR maGV LIKE ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) stmt.setString(i, kw);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new LopHP(
                    rs.getString("maHP"),
                    rs.getString("tenHP"),
                    rs.getString("maLop"),
                    rs.getString("maMon"),
                    rs.getString("maGV")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insert(LopHP l) {
        String sql = "INSERT INTO lophp (maHP, tenHP, maLop, maMon, maGV) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, l.getMaHP());
            stmt.setString(2, l.getTenHP());
            stmt.setString(3, l.getMaLop());
            stmt.setString(4, l.getMaMon());
            stmt.setString(5, l.getMaGV());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(LopHP l) {
        String sql = "UPDATE lophp SET tenHP=?, maLop=?, maMon=?, maGV=? WHERE maHP=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, l.getTenHP());
            stmt.setString(2, l.getMaLop());
            stmt.setString(3, l.getMaMon());
            stmt.setString(4, l.getMaGV());
            stmt.setString(5, l.getMaHP());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String maHP) {
        String sql = "DELETE FROM lophp WHERE maHP = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maHP);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
