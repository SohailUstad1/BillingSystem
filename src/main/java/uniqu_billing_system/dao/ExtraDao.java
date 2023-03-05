package uniqu_billing_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uniqu_billing_system.helper.ConnectionProvider;
import uniqu_billing_system.model.Extra;

public class ExtraDao {
	public boolean addExtra(Extra e) {
		boolean result = false;
		try {
			Connection con = new ConnectionProvider().getConnection();

			String q = "insert into extra(id,invoice_number,description,amount) values(?,?,?,?)";
			PreparedStatement p = con.prepareStatement(q);
			p.setLong(1, e.getId());
			p.setLong(2, e.getInvoice_number());
			p.setString(3, e.getDescription());
			p.setDouble(4, e.getAmount());
			p.executeUpdate();

			con.close();
			result = true;
		} catch (Exception ee) {
			// TODO: handle exception
			ee.printStackTrace();
		}
		return result;
	}

	public List<Extra> getExtraByInvoiceNumber(long iv) {
		List<Extra> list = new ArrayList<Extra>();
		try {
			Connection con = new ConnectionProvider().getConnection();

			String q = "select * from extra where invoice_number=?";
			PreparedStatement p = con.prepareStatement(q);
			p.setLong(1, iv);
			ResultSet res = p.executeQuery();
			while (res.next()) {
				Extra ex=new Extra();
				ex.setId(res.getLong("id"));
				ex.setInvoice_number(iv);
				ex.setDescription(res.getNString("description"));
				ex.setAmount(res.getDouble("amount"));
				list.add(ex);
			}

			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
