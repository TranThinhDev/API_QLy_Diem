package controller;

import com.google.gson.Gson;
import dao.NganhDAO;
import model.Nganh;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import static util.Utf8Util.setUtf8;

@WebServlet("/api/nganh")
public class NganhServlet extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    setUtf8(req, resp);
    String ma = req.getParameter("id");
    String keyword = req.getParameter("search");
    Gson gson = new Gson();

    if (ma != null && !ma.trim().isEmpty()) {
        // Ưu tiên tìm theo ID
        Nganh nganh = NganhDAO.getById(ma);
        resp.getWriter().print(gson.toJson(nganh));
    } else if (keyword != null && !keyword.trim().isEmpty()) {
        // Nếu có từ khóa tìm kiếm
        resp.getWriter().print(gson.toJson(NganhDAO.search(keyword)));
    } else {
        // Lấy toàn bộ danh sách
        resp.getWriter().print(gson.toJson(NganhDAO.getAll()));
    }
}


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        setUtf8(req, resp);
        BufferedReader reader = req.getReader();
        Nganh nganh = new Gson().fromJson(reader, Nganh.class);
        boolean ok = NganhDAO.insert(nganh);
        resp.getWriter().print(ok ? "success" : "error");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        setUtf8(req, resp);
        BufferedReader reader = req.getReader();
        Nganh nganh = new Gson().fromJson(reader, Nganh.class);
        boolean ok = NganhDAO.update(nganh);
        resp.getWriter().print(ok ? "updated" : "error");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        setUtf8(req, resp);
        String ma = req.getParameter("id");
        boolean ok = NganhDAO.delete(ma);
        resp.getWriter().print(ok ? "deleted" : "error");
    }

}
