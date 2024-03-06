package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoidao {
  public Connection cn;

  public void KetNoi() {
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      System.out.println("Da Nap Driver");
      String url = "jdbc:sqlserver://LAPTOP-6BDKCDG5\\QQ;databaseName=DocTruyen;user=sa;password=123";
      cn = DriverManager.getConnection(url);
      System.out.println("Da Ket Noi");
    } catch (Exception tt) {
      // TODO Auto-generated catch block
    	tt.printStackTrace();
    	System.out.println(tt.getMessage());
    }
  }
  public static void main(String[] args) {
	KetNoidao kn = new KetNoidao();
	kn.KetNoi();
}

}
