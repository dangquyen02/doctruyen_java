package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.theloaibean;
import bo.theloaibo;

@WebServlet("/AdminTheLoaiController")
public class AdminTheLoaiController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	HttpSession session = request.getSession();
            response.setCharacterEncoding("utf-8");
            request.setCharacterEncoding("utf-8");

            theloaibo tlbo = new theloaibo();
            ArrayList<theloaibean> dstheloai = tlbo.gettheloai();

            // hiển thị thông tin thể loại lên thẻ input khi kick vào tên thể loại
            String selecttl = request.getParameter("selecttheloai");
            if(selecttl != null) {
            	theloaibean tlbean = tlbo.cttheloai(Long.parseLong(selecttl));
            	session.setAttribute("selectmatl", tlbean.getMatheloai());
            	session.setAttribute("selecttentl", tlbean.getTentheloai());
            }

            String tab = request.getParameter("tab");
            String mtlStr = request.getParameter("txtmatheloai");
            String tentheloai = request.getParameter("txttentheloai");

            long mtl = 0;
            if (mtlStr != null && !mtlStr.isEmpty()) {
                mtl = Long.parseLong(mtlStr);
            }

            if (request.getParameter("butadd") != null) {
                tlbo.themTheLoai( tentheloai);
            } else if (request.getParameter("butupdate") != null) {
                tlbo.suaTheLoai(mtl, tentheloai);
            } else if (tab != null && tab.equals("xoa")) {
            	String mtlxoa = request.getParameter("mtl");
        	    long matheloai = Long.parseLong(mtlxoa);
        	    tlbo.xoaTheLoai(matheloai);
            }

            dstheloai = tlbo.gettheloai(); // Cập nhật ds sách sau khi thêm, sửa, xóa

            request.setAttribute("dstheloai", dstheloai);
            request.getRequestDispatcher("AdminTheLoai.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
