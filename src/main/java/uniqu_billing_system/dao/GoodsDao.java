package uniqu_billing_system.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uniqu_billing_system.helper.ConnectionProvider;

public class GoodsDao {
	public boolean addGoods(String g) {
		boolean result=false;
		try {
			Connection con=new ConnectionProvider().getConnection();
			String query="create table if not exists goods("
					+ "goods_id int(20) not null AUTO_INCREMENT,"
					+ "good varchar(100) not null unique,"
					+ "primary key(goods_id))";
			
			String q="insert into goods(good) values('"+g+"')";
			Statement s=con.createStatement();
			s.executeUpdate(query);
			s.executeUpdate(q);
			
			con.close();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String> getAllGoods() {
		List<String> list=new ArrayList<String>();
		try {
			Connection con = new ConnectionProvider().getConnection();
			String q = "select good from goods";
			Statement s=con.createStatement();
			ResultSet res = s.executeQuery(q);
			while(res.next()) {
				list.add(res.getString("good"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
