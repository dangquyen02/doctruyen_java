package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.httruyenbean;
import bean.nguoidungbean;
import bean.theloaibean;
import bo.httruyenbo;
import bo.theloaibo;

/**
 * Servlet implementation class ChiTietTruyenController
 */
@WebServlet("/ChiTietTruyenController")
public class ChiTietTruyenController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChiTietTruyenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			long matruyen = Long.parseLong(request.getParameter("mt")); // Lấy mã truyện
		    httruyenbo httbo = new httruyenbo();
		    httruyenbean truyen = httbo.getTruyenByMaTruyen(matruyen);
		    request.setAttribute("truyen", truyen);
		    
		    // thông tin đăng nhập
		    HttpSession session = request.getSession();
		    nguoidungbean nguoidung = (nguoidungbean) session.getAttribute("ktdn");
		    session.setAttribute("ktdn", nguoidung);
//		    if(nguoidung==null) {
//				nguoidung = new nguoidungbean();
//				session.setAttribute("ktdn", nguoidung);
//			}
		    // Thể loại truyện
			theloaibo tlbo = new theloaibo();
			ArrayList<theloaibean> dstheloai = tlbo.gettheloai();
			request.setAttribute("dstheloai", dstheloai);
			
			// ds truyện mới nhất
			ArrayList<httruyenbean> ds = httbo.getHTTruyen();
			session.setAttribute("dsTruyenMoiNhat", ds);
			
			// Chuyển hướng đến trang NoiDungTruyen.jsp, hiển thị thông tin chi tiết truyện
			RequestDispatcher rd = request.getRequestDispatcher("NoiDungTruyen.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
