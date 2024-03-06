package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.nguoidungbean;
import bo.nguoidungbo;

@WebServlet("/AdminNguoiDungController")
public class AdminNguoiDungController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	HttpSession session = request.getSession();
            response.setCharacterEncoding("utf-8");
            request.setCharacterEncoding("utf-8");

            nguoidungbo ndbo = new nguoidungbo();
            ArrayList<nguoidungbean> dsnguoidung = ndbo.getNguoiDung();

            // hiển thị thông tin người dùng lên thẻ input khi kick vào mã người dùng
            String selectnd = request.getParameter("selectnd");
            if(selectnd != null) {
            	nguoidungbean ndbean = ndbo.ctnguoidung(Long.parseLong(selectnd));
            	session.setAttribute("selectmand", ndbean.getManguoidung());
            	session.setAttribute("selecttennd", ndbean.getHoten());
            	//session.setAttribute("selectmk", ndbean.getMatkhau());
            	session.setAttribute("selecttendn", ndbean.getTendangnhap());
            }
            
            
            String tab = request.getParameter("tab");
            String mndStr = request.getParameter("txtmanguoidung");
            String tennguoidung = request.getParameter("txttennguoidung");
            String tendangnhap = request.getParameter("txttendangnhap");
          //mã hóa mật khẩu để lưu vào csdl
	        Mahoa mh = new Mahoa();
//	        String matkhau =mh.ecrypt( request.getParameter("txtmatkhau"));
            String matkhau = request.getParameter("txtmatkhau");

            long mnd = 0;
            if (mndStr != null && !mndStr.isEmpty()) {
                mnd = Long.parseLong(mndStr);
            }

            if (request.getParameter("butadd") != null) {
                ndbo.themNguoiDungAD(mnd, tennguoidung, tendangnhap, mh.ecrypt( request.getParameter("txtmatkhau")));	//matkhau
            } else if (request.getParameter("butupdate") != null) {
                ndbo.suaNguoiDung(mnd, tennguoidung, tendangnhap, mh.ecrypt( request.getParameter("txtmatkhau")));	//matkhau
            } else if (tab != null && tab.equals("xoa")) {
            	String mndxoa = request.getParameter("mnd");
        	    long manguoidung = Long.parseLong(mndxoa);
        	    ndbo.xoaNguoiDung(manguoidung);
            }

            dsnguoidung = ndbo.getNguoiDung();

            request.setAttribute("dsnguoidung", dsnguoidung);
            request.getRequestDispatcher("AdminNguoiDung.jsp").forward(request, response);
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
