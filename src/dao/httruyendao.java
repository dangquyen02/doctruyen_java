package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import bean.httruyenbean;

public class httruyendao {
	public ArrayList<httruyenbean> getHTTruyen() throws Exception {
		ArrayList<httruyenbean> ds = new ArrayList<httruyenbean>();
		KetNoidao kn = new KetNoidao();
		kn.KetNoi();
		String sql = "select * from V_HTTruyen order by matruyen desc";	//hiển thị 6 truyện mới nhất select top 6 * from V_HTTruyen order by matruyen desc
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while(rs.next()) {
			String anh = rs.getString("anh");
			long matruyen = rs.getLong("matruyen");
			String tentruyen = rs.getString("tentruyen");
			String mota = rs.getString("mota");
			String noidung = rs.getString("noidung");
			String tentheloai = rs.getString("tentheloai");
			String tentacgia = rs.getString("tentacgia");
			long matheloai = rs.getLong("matheloai");
			ds.add(new httruyenbean(anh, matruyen, tentruyen, mota, noidung, tentheloai, tentacgia, matheloai));
		}
		rs.close();
		kn.cn.close();
		return ds;
	}

	public httruyenbean getTruyenByMaTruyen(long matruyen) throws Exception {
        httruyenbean truyen = null;
        KetNoidao kn = new KetNoidao();
        kn.KetNoi();
        String sql = "SELECT * FROM V_HTTruyen WHERE matruyen = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, matruyen);
        ResultSet rs = cmd.executeQuery();
        while(rs.next()) {
            String anh = rs.getString("anh");
            String tentruyen = rs.getString("tentruyen");
            String mota = rs.getString("mota");
            String noidung = rs.getString("noidung");
            String tentheloai = rs.getString("tentheloai");
            String tentacgia = rs.getString("tentacgia");
            long matheloai = rs.getLong("matheloai");
            truyen = new httruyenbean(anh, matruyen, tentruyen, mota, noidung, tentheloai, tentacgia, matheloai);
        }
        rs.close();
        kn.cn.close();
        return truyen;
    }

//	public ArrayList<httruyenbean> getNextTop6HTTruyen(int amount) throws Exception {
//		ArrayList<httruyenbean> ds = new ArrayList<httruyenbean>();
//		KetNoidao kn = new KetNoidao();
//		kn.KetNoi();
//		String sql = "select * from V_HTTruyen\r\n"
//				+ "order by matruyen desc\r\n"
//				+ "offset ? rows \r\n"
//				+ "fetch next 6 rows only";
//		PreparedStatement cmd = kn.cn.prepareStatement(sql);
//		cmd.setInt(1, amount);
//		ResultSet rs = cmd.executeQuery();
//		while(rs.next()) {
//			String anh = rs.getString("anh");
//			long matruyen = rs.getLong("matruyen");
//			String tentruyen = rs.getString("tentruyen");
//			String mota = rs.getString("mota");
//			String noidung = rs.getString("noidung");
//			String tentheloai = rs.getString("tentheloai");
//			String tentacgia = rs.getString("tentacgia");
//			long matheloai = rs.getLong("matheloai");
//			ds.add(new httruyenbean(anh, matruyen, tentruyen, mota, noidung, tentheloai, tentacgia, matheloai));
//		}
//		rs.close();
//		kn.cn.close();
//		return ds;
//	}
}
