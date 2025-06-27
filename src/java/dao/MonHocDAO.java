package dao;

import ConnectDatabase.Conn;
import model.MonHoc;

import java.sql.*;
import java.util.*;

public class MonHocDAO {

    public static List<MonHoc> getAll() {
        List<MonHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM monhoc";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new MonHoc(
                    rs.getString("maMon"),
                    rs.getString("tenMon"),
                    rs.getInt("soTC")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static MonHoc getById(String maMon) {
        String sql = "SELECT * FROM monhoc WHERE maMon = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maMon);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MonHoc(
                    rs.getString("maMon"),
                    rs.getString("tenMon"),
                    rs.getInt("soTC")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static boolean insert(MonHoc mh) {
        String sql = "INSERT INTO monhoc (maMon, tenMon, soTC) VALUES (?, ?, ?)";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mh.getMaMon());
            stmt.setString(2, mh.getTenMon());
            stmt.setInt(3, mh.getSoTC());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean update(MonHoc mh) {
        String sql = "UPDATE monhoc SET tenMon = ?, soTC = ? WHERE maMon = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mh.getTenMon());
            stmt.setInt(2, mh.getSoTC());
            stmt.setString(3, mh.getMaMon());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean delete(String maMon) {
        String sql = "DELETE FROM monhoc WHERE maMon = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maMon);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static List<MonHoc> search(String keyword) {
        List<MonHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM monhoc WHERE tenMon LIKE ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new MonHoc(
                    rs.getString("maMon"),
                    rs.getString("tenMon"),
                    rs.getInt("soTC")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
