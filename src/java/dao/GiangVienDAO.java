package dao;

import ConnectDatabase.Conn;
import model.GiangVien;
import java.sql.*;
import java.util.*;

public class GiangVienDAO {

    public static List<GiangVien> getAll() {
        List<GiangVien> list = new ArrayList<>();
        String sql = "SELECT * FROM giangvien";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new GiangVien(
                    rs.getString("maGV"),
                    rs.getString("hoTen"),
                    rs.getString("diaChi"),
                    rs.getString("email"),
                    rs.getString("sdt"),
                    rs.getString("thanhTich"),
                    rs.getString("khenThuong")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static GiangVien getById(String maGV) {
        String sql = "SELECT * FROM giangvien WHERE maGV = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maGV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new GiangVien(
                    rs.getString("maGV"),
                    rs.getString("hoTen"),
                    rs.getString("diaChi"),
                    rs.getString("email"),
                    rs.getString("sdt"),
                    rs.getString("thanhTich"),
                    rs.getString("khenThuong")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static List<GiangVien> search(String keyword) {
        List<GiangVien> list = new ArrayList<>();
        String sql = "SELECT * FROM giangvien WHERE maGV LIKE ? OR hoTen LIKE ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String like = "%" + keyword + "%";
            stmt.setString(1, like);
            stmt.setString(2, like);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new GiangVien(
                    rs.getString("maGV"),
                    rs.getString("hoTen"),
                    rs.getString("diaChi"),
                    rs.getString("email"),
                    rs.getString("sdt"),
                    rs.getString("thanhTich"),
                    rs.getString("khenThuong")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insert(GiangVien gv) {
        String sql = "INSERT INTO giangvien VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, gv.getMaGV());
            stmt.setString(2, gv.getHoTen());
            stmt.setString(3, gv.getDiaChi());
            stmt.setString(4, gv.getEmail());
            stmt.setString(5, gv.getSdt());
            stmt.setString(6, gv.getThanhTich());
            stmt.setString(7, gv.getKhenThuong());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean update(GiangVien gv) {
        String sql = "UPDATE giangvien SET hoTen=?, diaChi=?, email=?, sdt=?, thanhTich=?, khenThuong=? WHERE maGV=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, gv.getHoTen());
            stmt.setString(2, gv.getDiaChi());
            stmt.setString(3, gv.getEmail());
            stmt.setString(4, gv.getSdt());
            stmt.setString(5, gv.getThanhTich());
            stmt.setString(6, gv.getKhenThuong());
            stmt.setString(7, gv.getMaGV());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean delete(String maGV) {
        String sql = "DELETE FROM giangvien WHERE maGV=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maGV);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
