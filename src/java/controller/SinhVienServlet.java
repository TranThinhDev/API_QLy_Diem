package controller;

import com.google.gson.Gson;
import dao.SinhVienDAO;
import model.SinhVien;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.util.List;

@WebServlet("/api/sinhvien")
public class SinhVienServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String id = req.getParameter("id");
        String keyword = req.getParameter("search");

        if (id != null) {
            SinhVien sv = SinhVienDAO.getById(id);
            resp.getWriter().print(gson.toJson(sv));
        } else if (keyword != null) {
            List<SinhVien> result = SinhVienDAO.search(keyword);
            resp.getWriter().print(gson.toJson(result));
        } else {
            resp.getWriter().print(gson.toJson(SinhVienDAO.getAll()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        SinhVien sv = gson.fromJson(reader, SinhVien.class);
        boolean success = SinhVienDAO.insert(sv);
        resp.getWriter().print(success ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        SinhVien sv = gson.fromJson(req.getReader(), SinhVien.class);
        boolean success = SinhVienDAO.update(sv);
        resp.getWriter().print(success ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        boolean success = SinhVienDAO.delete(id);
        resp.getWriter().print(success ? "deleted" : "error");
    }
}
