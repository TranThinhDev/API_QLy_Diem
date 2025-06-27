package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.LoginDAO;
import model.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private static final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Đọc JSON từ body
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        // Parse JSON input
        JsonObject jsonInput = gson.fromJson(sb.toString(), JsonObject.class);
        String tenDN = jsonInput.get("username").getAsString();
        String matKhau = jsonInput.get("password").getAsString();

        TaiKhoan tk = LoginDAO.checkLogin(tenDN, matKhau);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (tk != null) {
            // Trả về thông tin tài khoản ở dạng JSON
            String jsonOutput = gson.toJson(tk);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonOutput);
        } else {
            // Trả về lỗi nếu đăng nhập thất bại
            JsonObject error = new JsonObject();
            error.addProperty("error", "Sai tên đăng nhập hoặc mật khẩu");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(error.toString());
        }
    }
}
