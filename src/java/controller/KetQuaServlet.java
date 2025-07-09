package controller;

import com.google.gson.Gson;
import dao.KetQuaDAO;
import model.KetQua;
import util.Utf8Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/api/ketqua")
public class KetQuaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        Gson gson = new Gson();

        String maSV = req.getParameter("maSV");
        String maMon = req.getParameter("maMon");
        String lanHocStr = req.getParameter("lanHoc");
        String search = req.getParameter("search");

        if (maSV != null && maMon != null && lanHocStr != null) {
            int lanHoc = Integer.parseInt(lanHocStr);
            KetQua kq = KetQuaDAO.getById(maSV, maMon, lanHoc);
            resp.getWriter().print(gson.toJson(kq));
        } else if (search != null) {
            resp.getWriter().print(gson.toJson(KetQuaDAO.search(search)));
        } else {
            resp.getWriter().print(gson.toJson(KetQuaDAO.getAll()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        KetQua kq = new Gson().fromJson(req.getReader(), KetQua.class);
        boolean ok = KetQuaDAO.insert(kq);
        resp.getWriter().print(ok ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        KetQua kq = new Gson().fromJson(req.getReader(), KetQua.class);
        boolean ok = KetQuaDAO.update(kq);
        resp.getWriter().print(ok ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Utf8Util.setUtf8(req, resp);
        String maSV = req.getParameter("maSV");
        String maMon = req.getParameter("maMon");
        String lanHocStr = req.getParameter("lanHoc");

        if (maSV != null && maMon != null && lanHocStr != null) {
            int lanHoc = Integer.parseInt(lanHocStr);
            boolean ok = KetQuaDAO.delete(maSV, maMon, lanHoc);
            resp.getWriter().print(ok ? "deleted" : "error");
        } else {
            resp.getWriter().print("error");
        }
    }
}
