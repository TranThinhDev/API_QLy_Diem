package dao;

import ConnectDatabase.Conn;
import model.Nganh;
import util.DbUtil;

import java.sql.*;
import java.util.*;

public class NganhDAO {

    public static List<Nganh> getAll() {
        List<Nganh> list = new ArrayList<>();
        String sql = "SELECT * FROM nganh";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conn.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Nganh(
                    rs.getString("maNganh"),
                    rs.getString("tenNganh"),
                    rs.getString("tenKhoa")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn, stmt, rs);
        }
        return list;
    }

    public static Nganh getById(String maNganh) {
        String sql = "SELECT * FROM nganh WHERE maNganh = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNganh);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Nganh(
                    rs.getString("maNganh"),
                    rs.getString("tenNganh"),
                    rs.getString("tenKhoa")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn, stmt, rs);
        }
        return null;
    }
    
    public static List<Nganh> search(String keyword) {
    List<Nganh> list = new ArrayList<>();
    String sql = "SELECT * FROM nganh WHERE maNganh LIKE ? OR tenNganh LIKE ?";

    try (Connection conn = Conn.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        String like = "%" + keyword + "%";
        stmt.setString(1, like);
        stmt.setString(2, like);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Nganh(
                    rs.getString("maNganh"),
                    rs.getString("tenNganh"),
                    rs.getString("tenKhoa")
                ));
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}


    public static boolean insert(Nganh nganh) {
        String sql = "INSERT INTO nganh(maNganh, tenNganh, tenKhoa) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nganh.getMaNganh());
            stmt.setString(2, nganh.getTenNganh());
            stmt.setString(3, nganh.getTenKhoa());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn, stmt, null);
        }
        return false;
    }

    public static boolean update(Nganh nganh) {
        String sql = "UPDATE nganh SET tenNganh = ?, tenKhoa = ? WHERE maNganh = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nganh.getTenNganh());
            stmt.setString(2, nganh.getTenKhoa());
            stmt.setString(3, nganh.getMaNganh());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn, stmt, null);
        }
        return false;
    }

    public static boolean delete(String maNganh) {
        String sql = "DELETE FROM nganh WHERE maNganh = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNganh);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn, stmt, null);
        }
        return false;
    }
}
