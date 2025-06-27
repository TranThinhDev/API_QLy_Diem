
package controller;

import com.google.gson.Gson;
import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import util.Utf8Util;

public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        if (id != null) {
            User user = UserDAO.getUserById(Integer.parseInt(id));
            resp.getWriter().write(new Gson().toJson(user));
        } else {
            List<User> list = UserDAO.getAllUsers();
            resp.getWriter().write(new Gson().toJson(list));
        }
    }
    
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utf8Util.setUtf8(req, resp);
        User user = new Gson().fromJson(req.getReader(), User.class);
        boolean success = UserDAO.insertUser(user);
        resp.getWriter().write("{\"success\":" + success + "}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utf8Util.setUtf8(req, resp);
        User user = new Gson().fromJson(req.getReader(), User.class);
        boolean success = UserDAO.updateUser(user);
        resp.getWriter().write("{\"success\":" + success + "}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean success = UserDAO.deleteUser(id);
        resp.getWriter().write("{\"success\":" + success + "}");
    }

}
