package controller;

import com.google.gson.Gson;
import dao.BangDiemDAO;
import model.BangDiem;
import util.Utf8Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/api/bangdiem")
public class BangDiemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        Gson gson = new Gson();

        String maSV = req.getParameter("maSV");

        if (maSV != null) {
            BangDiem bd = BangDiemDAO.getById(maSV);
            resp.getWriter().print(gson.toJson(bd));
        } else {
            resp.getWriter().print(gson.toJson(BangDiemDAO.getAll()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        BangDiem bd = new Gson().fromJson(req.getReader(), BangDiem.class);
        boolean ok = BangDiemDAO.insert(bd);
        resp.getWriter().print(ok ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        BangDiem bd = new Gson().fromJson(req.getReader(), BangDiem.class);
        boolean ok = BangDiemDAO.update(bd);
        resp.getWriter().print(ok ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        String maSV = req.getParameter("maSV");
        boolean ok = BangDiemDAO.delete(maSV);
        resp.getWriter().print(ok ? "deleted" : "error");
    }
}
