package bo;

import java.util.ArrayList;



import bean.httruyenbean;
import dao.httruyendao;

public class httruyenbo {
	httruyendao httdao = new httruyendao();
	ArrayList<httruyenbean> ds;
	
	public ArrayList<httruyenbean> getHTTruyen() throws Exception {
		ds=httdao.getHTTruyen();
		return ds;
	}

		public ArrayList<httruyenbean> timTheLoai(long matheloai) {
			ArrayList<httruyenbean> tam = new ArrayList<httruyenbean>();
			for (httruyenbean truyen : ds)
				if(truyen.getMatheloai()==matheloai)
					tam.add(truyen);
			return tam;
		}

		public ArrayList<httruyenbean> timKiem(String key) throws Exception {
			ArrayList<httruyenbean> tam = new ArrayList<httruyenbean>();
			for (httruyenbean truyen : ds)
				if(truyen.getTentacgia().toLowerCase().contains(key.toLowerCase()) ||
						truyen.getTentruyen().toLowerCase().contains(key.toLowerCase()))
					tam.add(truyen);
			return tam;
		}
		
		public boolean isSearch(ArrayList<httruyenbean> dshttruyen) {
		    return dshttruyen.isEmpty();
		}

		public httruyenbean getTruyenByMaTruyen(long mt) throws Exception {
			return httdao.getTruyenByMaTruyen(mt);
		}

//		public ArrayList<httruyenbean> getNextTop6HTTruyen(int amount) throws Exception {
//			return httdao.getNextTop6HTTruyen(amount);
//		}

}
