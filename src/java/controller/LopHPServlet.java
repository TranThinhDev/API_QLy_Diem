package controller;

import com.google.gson.Gson;
import dao.LopHPDAO;
import model.LopHP;
import util.Utf8Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;

@WebServlet("/api/lophp")
public class LopHPServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        String id = req.getParameter("id");
        String search = req.getParameter("search");
        Gson gson = new Gson();

        if (id != null) {
            LopHP lop = LopHPDAO.getById(id);
            resp.getWriter().print(gson.toJson(lop));
        } else if (search != null) {
            resp.getWriter().print(gson.toJson(LopHPDAO.search(search)));
        } else {
            resp.getWriter().print(gson.toJson(LopHPDAO.getAll()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        LopHP lop = new Gson().fromJson(req.getReader(), LopHP.class);
        boolean ok = LopHPDAO.insert(lop);
        resp.getWriter().print(ok ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        LopHP lop = new Gson().fromJson(req.getReader(), LopHP.class);
        boolean ok = LopHPDAO.update(lop);
        resp.getWriter().print(ok ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        String id = req.getParameter("id");
        boolean ok = LopHPDAO.delete(id);
        resp.getWriter().print(ok ? "deleted" : "error");
    }
}

