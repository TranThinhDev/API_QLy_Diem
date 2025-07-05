package controller;

import com.google.gson.Gson;
import dao.LopDAO;
import model.Lop;
import util.DbUtil;
import util.Utf8Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;

@WebServlet("/api/lop")
public class LopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        String id = req.getParameter("id");
        String search = req.getParameter("search");
        Gson gson = new Gson();

        if (id != null) {
            Lop lop = LopDAO.getById(id);
            resp.getWriter().print(gson.toJson(lop));
        } else if (search != null) {
            resp.getWriter().print(gson.toJson(LopDAO.search(search)));
        } else {
            resp.getWriter().print(gson.toJson(LopDAO.getAll()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        Lop lop = new Gson().fromJson(req.getReader(), Lop.class);
        boolean ok = LopDAO.insert(lop);
        resp.getWriter().print(ok ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        Lop lop = new Gson().fromJson(req.getReader(), Lop.class);
        boolean ok = LopDAO.update(lop);
        resp.getWriter().print(ok ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        String id = req.getParameter("id");
        boolean ok = LopDAO.delete(id);
        resp.getWriter().print(ok ? "deleted" : "error");
    }
}
