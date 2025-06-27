package controller;

import com.google.gson.Gson;
import dao.TaiKhoanDAO;
import model.TaiKhoan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import util.Utf8Util;
@WebServlet("/api/account")
public class AccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json; charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        String id = req.getParameter("id");
        if (id != null) {
            TaiKhoan tk = TaiKhoanDAO.getByUsername(id);
            out.print(new Gson().toJson(tk));
        } else {
            List<TaiKhoan> list = TaiKhoanDAO.getAll();
            out.print(new Gson().toJson(list));
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Utf8Util.setUtf8(req, res);
        TaiKhoan tk = new Gson().fromJson(req.getReader(), TaiKhoan.class);
        boolean success = TaiKhoanDAO.insert(tk);
        res.getWriter().print("{\"success\": " + success + "}");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Utf8Util.setUtf8(req, res);
        TaiKhoan tk = new Gson().fromJson(req.getReader(), TaiKhoan.class);
        boolean success = TaiKhoanDAO.update(tk);
        res.getWriter().print("{\"success\": " + success + "}");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String tenDN = req.getParameter("tenDN");
        boolean success = TaiKhoanDAO.delete(tenDN);
        res.getWriter().print("{\"success\": " + success + "}");
    }
}
