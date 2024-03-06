package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.admintaikhoanbean;
import bo.admintaikhoanbo;

@WebServlet("/AdminDangNhapController")
public class AdminDangNhapController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setCharacterEncoding("utf-8");
            request.setCharacterEncoding("utf-8");

            String taikhoanadmin = request.getParameter("usernameadmin");
            String matkhauadmin = request.getParameter("passwordadmin");

            admintaikhoanbo tkadminbo = new admintaikhoanbo();
            admintaikhoanbean tkadmin = tkadminbo.ktdnadmin(taikhoanadmin, matkhauadmin);

            if (tkadmin != null) {
                // Đăng nhập thành công, lấy thông tin vào session
                HttpSession session = request.getSession();
                session.setAttribute("tkadmin", tkadmin);
                response.sendRedirect("AdminTrangChuController"); // Chuyển hướng đến trang quản lý
            } else {
                // Đăng nhập thất bại, gán thông báo...
                request.setAttribute("errorMessage", "Tài khoản hoặc mật khẩu không chính xác");
                RequestDispatcher rd = request.getRequestDispatcher("AdminDangNhap.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			RequestDispatcher rd = request.getRequestDispatcher("AdminDangNhap.jsp");
			rd.forward(request, response);
//        response.sendRedirect("AdminDangNhap.jsp"); // Chuyển hướng đến trang đăng nhập
    }
}
