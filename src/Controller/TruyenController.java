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
 * Servlet implementation class TheLoaiController
 */
@WebServlet("/TruyenController")
public class TruyenController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TruyenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");

			String key = request.getParameter("txttim");
			String mtlSt = request.getParameter("mtl");
			long mtl = 0;
			
			//**
			nguoidungbean nguoidung = (nguoidungbean) session.getAttribute("ktdn");
				session.setAttribute("ktdn", nguoidung);
			//**
			
			if (mtlSt != null && !mtlSt.isEmpty()) {
			    mtl = Long.parseLong(mtlSt);
			}
			theloaibo tlbo = new theloaibo();
			ArrayList<theloaibean> dstheloai = tlbo.gettheloai();

			httruyenbo httbo = new httruyenbo();
			ArrayList<httruyenbean> dshttruyen = httbo.getHTTruyen();

			if(mtl!=0) 
				dshttruyen =httbo.timTheLoai(mtl);
			else
			if(key!=null)
				dshttruyen =httbo.timKiem(key);

			request.setAttribute("dstheloai", dstheloai);
			request.setAttribute("dshttruyen", dshttruyen);
			RequestDispatcher rd = request.getRequestDispatcher("TrangChu.jsp");
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
