package dao;

import ConnectDatabase.Conn;
import model.KetQua;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KetQuaDAO {

    public static List<KetQua> getAll() {
        List<KetQua> list = new ArrayList<>();
        String sql = "SELECT * FROM ketqua";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(getKetQuaFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static KetQua getById(String maSV, String maMon, int lanHoc) {
        String sql = "SELECT * FROM ketqua WHERE maSV=? AND maMon=? AND lanHoc=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            stmt.setString(2, maMon);
            stmt.setInt(3, lanHoc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return getKetQuaFromResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<KetQua> search(String keyword) {
        List<KetQua> list = new ArrayList<>();
        String sql = "SELECT * FROM ketqua WHERE maSV LIKE ? OR maMon LIKE ? OR maHP LIKE ?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String kw = "%" + keyword + "%";
            stmt.setString(1, kw);
            stmt.setString(2, kw);
            stmt.setString(3, kw);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(getKetQuaFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insert(KetQua kq) {
        String sql = "INSERT INTO ketqua(maSV, maMon, lanHoc, diemCC, diemKT, diemThiLan1, diemThiLan2, diemTK, hocKy, ghiChu, maHP, danhGia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setStatementParams(stmt, kq);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(KetQua kq) {
        String sql = "UPDATE ketqua SET diemCC=?, diemKT=?, diemThiLan1=?, diemThiLan2=?, diemTK=?, hocKy=?, ghiChu=?, maHP=?, danhGia=? WHERE maSV=? AND maMon=? AND lanHoc=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, kq.getDiemCC());
            stmt.setDouble(2, kq.getDiemKT());
            stmt.setDouble(3, kq.getDiemThiLan1());
            stmt.setDouble(4, kq.getDiemThiLan2());
            stmt.setDouble(5, kq.getDiemTK());
            stmt.setString(6, kq.getHocKy());
            stmt.setString(7, kq.getGhiChu());
            stmt.setString(8, kq.getMaHP());
            stmt.setString(9, kq.getDanhGia());
            stmt.setString(10, kq.getMaSV());
            stmt.setString(11, kq.getMaMon());
            stmt.setInt(12, kq.getLanHoc());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String maSV, String maMon, int lanHoc) {
        String sql = "DELETE FROM ketqua WHERE maSV=? AND maMon=? AND lanHoc=?";
        try (Connection conn = Conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSV);
            stmt.setString(2, maMon);
            stmt.setInt(3, lanHoc);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static KetQua getKetQuaFromResultSet(ResultSet rs) throws SQLException {
        return new KetQua(
                rs.getString("maSV"),
                rs.getString("maMon"),
                rs.getInt("lanHoc"),
                rs.getDouble("diemCC"),
                rs.getDouble("diemKT"),
                rs.getDouble("diemThiLan1"),
                rs.getDouble("diemThiLan2"),
                rs.getDouble("diemTK"),
                rs.getString("hocKy"),
                rs.getString("ghiChu"),
                rs.getString("maHP"),
                rs.getString("danhGia")
        );
    }

    private static void setStatementParams(PreparedStatement stmt, KetQua kq) throws SQLException {
        stmt.setString(1, kq.getMaSV());
        stmt.setString(2, kq.getMaMon());
        stmt.setInt(3, kq.getLanHoc());
        stmt.setDouble(4, kq.getDiemCC());
        stmt.setDouble(5, kq.getDiemKT());
        stmt.setDouble(6, kq.getDiemThiLan1());
        stmt.setDouble(7, kq.getDiemThiLan2());
        stmt.setDouble(8, kq.getDiemTK());
        stmt.setString(9, kq.getHocKy());
        stmt.setString(10, kq.getGhiChu());
        stmt.setString(11, kq.getMaHP());
        stmt.setString(12, kq.getDanhGia());
    }
}
