package bo;

import java.util.ArrayList;

import bean.theloaibean;
import dao.theloaidao;

public class theloaibo {
	theloaidao tldao = new theloaidao();
	ArrayList<theloaibean> ds;
	public ArrayList<theloaibean> gettheloai() throws Exception {
		return tldao.gettheloai();
	}
	
	public theloaibean cttheloai(long matl) throws Exception {
		return tldao.cttheloai(matl);
	}

	public int getTongTheLoai() throws Exception {
		return tldao.getTongTheLoai();
	}

	public int xoaTheLoai(long matheloai) throws Exception {
		return tldao.xoaTheLoai(matheloai);
	}

	public int themTheLoai( String tentheloai) throws Exception {
		return tldao.themTheLoai(tentheloai);
	}

	public int suaTheLoai(long matheloai, String tentheloaimoi) throws Exception {
		return tldao.suaTheLoai(matheloai, tentheloaimoi);
	}


}
