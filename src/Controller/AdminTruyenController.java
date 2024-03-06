package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import bean.truyenbean;
import bo.truyenbo;


//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/AdminTruyenController")
public class AdminTruyenController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
    	   HttpSession session = request.getSession();
            response.setCharacterEncoding("utf-8");
            request.setCharacterEncoding("utf-8");

            truyenbo trbo = new truyenbo();
            ArrayList<truyenbean> dstruyen = trbo.gettruyen();
            
            // hiển thị thông tin lên thẻ input khi kick vào tên truyện
            String tt = request.getParameter("tt");
            if(tt != null) {
            	truyenbean trbean = trbo.cttruyen(Long.parseLong(tt));
            	//request.setAttribute("t", trbean);
            	session.setAttribute("selectmatr", trbean.getMatruyen());
            	session.setAttribute("selecttentr", trbean.getTentruyen());
            	session.setAttribute("selectanh", trbean.getAnh());
            	session.setAttribute("selectnd", trbean.getNoidung());
            	session.setAttribute("selectmota", trbean.getMota());
            	session.setAttribute("selectmatg", trbean.getMatacgia());
            	session.setAttribute("selectmatheloai", trbean.getMatheloai());
            	//response.sendRedirect("AdminTruyenController");
            }
            //
            String tab = request.getParameter("tab");	//kick xóa
            String mtrStr = request.getParameter("txtmatruyen");
            String tentruyen = request.getParameter("txttentruyen");
            String anh = request.getParameter("txtanh");
            String noidung = request.getParameter("txtnoidung");
            String mota = request.getParameter("txtmota");
            String mtgStr = request.getParameter("txtmatacgia");
            String mtlStr = request.getParameter("txtmatheloai");
            
            long mtr = 0;
            if (mtrStr != null && !mtrStr.isEmpty()) {
                mtr = Long.parseLong(mtrStr);
            }

            long mtg = 0;
            if (mtgStr != null && !mtgStr.isEmpty()) {
                mtg = Long.parseLong(mtgStr);
            }

            long mtl = 0;
            if (mtlStr != null && !mtlStr.isEmpty()) {
                mtl = Long.parseLong(mtlStr);
            }

            if (request.getParameter("butadd") != null) {
                trbo.themTruyen(tentruyen, anh, noidung, mota, mtg, mtl);
            } else if (request.getParameter("butupdate") != null) {
                trbo.suaTruyen(mtr, tentruyen, anh, noidung, mota, mtg, mtl);
            } else if (tab != null && tab.equals("xoa")) {
            	String mtrxoa = request.getParameter("mtr");
        	    long matruyen = Long.parseLong(mtrxoa);
        	    trbo.xoaTruyen(matruyen);
            }

            dstruyen = trbo.gettruyen();

            request.setAttribute("dstruyen", dstruyen);
            request.getRequestDispatcher("AdminTruyen.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    	
 /*   	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		String dirUrl1 = request.getServletContext().getRealPath("") + File.separator + "img-truyen";
		response.getWriter().println(dirUrl1);
		
		truyenbo trbo = new truyenbo();
        try {
        	if(request.getContentLength()<=0) {//Chay lan dau
				RequestDispatcher rd= request.getRequestDispatcher("AdminTruyen.jsp");
				rd.forward(request, response);
				return;
			}
        	List<FileItem> fileItems = upload.parseRequest(request);// Lấy về các đối tượng gửi lên
			long matruyen = 0;
			String tentruyen = "";
			String anh = "";
			String noidung = "";
			String mota = "";
			long matg = 0;
			long matl = 0;
			// duyệt qua các đối tượng gửi lên từ client gồm file và các control
						for (FileItem fileItem : fileItems) {
							if (!fileItem.isFormField()) {// Nếu ko phải các control=>upfile lên
								// xử lý file
								String nameimg = fileItem.getName();
								if (!nameimg.equals("")) {
									// Lấy đường dẫn hiện tại, chủ ý xử lý trên dirUrl để có đường dẫn đúng
									String dirUrl = request.getServletContext().getRealPath("") + File.separator + "img-truyen";	//
									File dir = new File(dirUrl);
									if (!dir.exists()) {// nếu ko có thư mục thì tạo ra
										dir.mkdir();
									}
									String fileImg = dirUrl + File.separator + nameimg;
			                        File file = new File(fileImg);
			                        try {
			                            fileItem.write(file);
			                            anh = "img-truyen" + "/" + nameimg;  
			                            System.out.println("UPLOAD THÀNH CÔNG...!");
			                        } catch (Exception e) {
			                            e.printStackTrace();
			                        }
								}
							} else// Neu la control
							{
								// Form fields
			                    String fieldName = fileItem.getFieldName();
			                    String fieldValue = fileItem.getString();

			                    switch (fieldName) {
			                        case "txtmatruyen":
			                        	matruyen = Long.parseLong(fieldValue);
			                            break;
			                        case "txttentruyen":
			                        	tentruyen = fieldValue;
			                            break;
			                        case "txtnoidung":
			                        	noidung = fieldValue;
			                            break;
			                        case "txtmota":
			                        	mota = fieldValue;
			                            break;
			                        case "txtmatacgia":
			                        	matg = Long.parseLong(fieldValue);
			                            break;
			                        case "txtanh":
			                        	anh = "image_sach" + "/" + fieldValue;
			                            break;
			                        case "txtmatheloai":
			                        	matl = Long.parseLong(fieldValue);
			                            break;
			                        case "butadd":
			                        	trbo.themTruyen(tentruyen, anh, noidung, mota, matg, matl);
			                        	System.out.println("Thêm truyện okela!");
			                        	response.sendRedirect("AdminTruyenController");
			                        	//break;
//			                        case "butupdate":
//			                        	System.out.println("mã sách sắ");
//			                        	trbo.suaTruyen(matruyen, tentruyen, anh, noidung, mota, matg, matl);
//			                        	response.sendRedirect("AdminTruyenController");	                        	
			                    }
							}
						}
						
//						ArrayList<truyenbean> dstruyen = trbo.gettruyen();
//			            dstruyen = trbo.gettruyen();
//
//			            request.setAttribute("dstruyen", dstruyen);
						 String tab = request.getParameter("tab");
						 if (tab != null && tab.equals("xoa")) {
			            	String mtrxoa = request.getParameter("mtr");
			        	    long matruyenx = Long.parseLong(mtrxoa);
			        	    trbo.xoaTruyen(matruyenx);
			            }

			            
			            
			            request.getRequestDispatcher("AdminTruyen.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
    	
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
