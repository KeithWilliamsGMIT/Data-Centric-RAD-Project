package ie.gmit.rad;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/garage";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	// Return an array list of all manufacturers from the database
	public ArrayList<Manufacturer> getManufacturers() throws Exception {
		ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("SELECT * " +
					 									"FROM manufacturer");
		
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			String manuCode = rs.getString("manu_name");
			String manuName = rs.getString("manu_code");
			String manuDetails = rs.getString("manu_details");
			manufacturers.add(new Manufacturer(manuCode, manuName, manuDetails));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return manufacturers;
	}
}
