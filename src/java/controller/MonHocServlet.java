package controller;

import com.google.gson.Gson;
import dao.MonHocDAO;
import model.MonHoc;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.util.List;

@WebServlet("/api/monhoc")
public class MonHocServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        String ma = req.getParameter("id");
        String search = req.getParameter("search");
        Gson gson = new Gson();

        if (ma != null) {
            MonHoc mh = MonHocDAO.getById(ma);
            resp.getWriter().print(gson.toJson(mh));
        } else if (search != null) {
            List<MonHoc> list = MonHocDAO.search(search);
            resp.getWriter().print(gson.toJson(list));
        } else {
            List<MonHoc> list = MonHocDAO.getAll();
            resp.getWriter().print(gson.toJson(list));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        MonHoc mh = new Gson().fromJson(reader, MonHoc.class);
        boolean ok = MonHocDAO.insert(mh);
        resp.getWriter().print(ok ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        MonHoc mh = new Gson().fromJson(reader, MonHoc.class);
        boolean ok = MonHocDAO.update(mh);
        resp.getWriter().print(ok ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String ma = req.getParameter("id");
        boolean ok = MonHocDAO.delete(ma);
        resp.getWriter().print(ok ? "deleted" : "error");
    }
}
