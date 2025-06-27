package dao;

import ConnectDatabase.Conn;
import model.SinhVien;
import java.sql.*;
import java.util.*;

public class SinhVienDAO {

    public static List<SinhVien> getAll() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien ORDER BY maSV ASC";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(extract(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static SinhVien getById(String maSV) {
        String sql = "SELECT * FROM sinhvien WHERE maSV = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extract(rs);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static List<SinhVien> search(String keyword) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien WHERE hoTen LIKE ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static boolean insert(SinhVien sv) {
        String sql = "INSERT INTO sinhvien(maSV, hoTen, maLop, ngaySinh, gioiTinh, email, sdt, diaChi, trangThai, khoaHoc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParams(stmt, sv);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean update(SinhVien sv) {
        String sql = "UPDATE sinhvien SET hoTen=?, maLop=?, ngaySinh=?, gioiTinh=?, email=?, sdt=?, diaChi=?, trangThai=?, khoaHoc=? WHERE maSV=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setParams(stmt, sv);
            stmt.setString(10, sv.getMaSV());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean delete(String maSV) {
        String sql = "DELETE FROM sinhvien WHERE maSV = ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    private static SinhVien extract(ResultSet rs) throws SQLException {
        return new SinhVien(
            rs.getString("maSV"),
            rs.getString("hoTen"),
            rs.getString("maLop"),
            rs.getString("ngaySinh"),
            rs.getString("gioiTinh"),
            rs.getString("email"),
            rs.getString("sdt"),
            rs.getString("diaChi"),
            rs.getString("trangThai"),
            rs.getString("khoaHoc")
        );
    }

    private static void setParams(PreparedStatement stmt, SinhVien sv) throws SQLException {
        stmt.setString(1, sv.getMaSV());
        stmt.setString(2, sv.getHoTen());
        stmt.setString(3, sv.getMaLop());
        stmt.setString(4, sv.getNgaySinh());
        stmt.setString(5, sv.getGioiTinh());
        stmt.setString(6, sv.getEmail());
        stmt.setString(7, sv.getSdt());
        stmt.setString(8, sv.getDiaChi());
        stmt.setString(9, sv.getTrangThai());
        stmt.setString(10, sv.getKhoaHoc());
    }
}
