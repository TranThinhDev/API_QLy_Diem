package controller;

import com.google.gson.Gson;
import dao.GiangVienDAO;
import model.GiangVien;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import util.Utf8Util;

@WebServlet("/api/giangvien")
public class GiangVienServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utf8Util.setUtf8(req, resp);
        Gson gson = new Gson();

        String ma = req.getParameter("id");
        String keyword = req.getParameter("search");

        if (ma != null && !ma.isEmpty()) {
            resp.getWriter().print(gson.toJson(GiangVienDAO.getById(ma)));
        } else if (keyword != null && !keyword.isEmpty()) {
            resp.getWriter().print(gson.toJson(GiangVienDAO.search(keyword)));
        } else {
            resp.getWriter().print(gson.toJson(GiangVienDAO.getAll()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utf8Util.setUtf8(req, resp);
        BufferedReader reader = req.getReader();
        GiangVien gv = new Gson().fromJson(reader, GiangVien.class);
        boolean ok = GiangVienDAO.insert(gv);
        resp.getWriter().print(ok ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utf8Util.setUtf8(req, resp);
        BufferedReader reader = req.getReader();
        GiangVien gv = new Gson().fromJson(reader, GiangVien.class);
        boolean ok = GiangVienDAO.update(gv);
        resp.getWriter().print(ok ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ma = req.getParameter("id");
        boolean ok = GiangVienDAO.delete(ma);
        resp.getWriter().print(ok ? "deleted" : "error");
    }
}
