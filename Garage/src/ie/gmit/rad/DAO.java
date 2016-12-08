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
	
	// Constructors
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
			String manuCode = rs.getString("manu_code");
			String manuName = rs.getString("manu_name");
			String manuDetails = rs.getString("manu_details");
			manufacturers.add(new Manufacturer(manuCode, manuName, manuDetails));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return manufacturers;
	}
	
	// Delete the given manufacturer from the manufacturer table
	public void deleteManufacturer(Manufacturer manufacturer) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("DELETE FROM manufacturer " +
					 									"WHERE manu_code=?");
		
		myStmt.setString(1, manufacturer.getManuCode());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Insert a new manufacturer into the manufacturer table
	public void addManufacturer(Manufacturer newManufacturer) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO manufacturer " +
					 									"VALUES (?,?,?)");
		
		myStmt.setString(1, newManufacturer.getManuCode());
		myStmt.setString(2, newManufacturer.getManuName());
		myStmt.setString(3, newManufacturer.getManuDetails());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Return an array list of all models from the database
	public ArrayList<Model> getModels() throws Exception {
		ArrayList<Model> models = new ArrayList<Model>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("SELECT * " +
					 									"FROM model");
		
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			String manuCode = rs.getString("manu_code");
			Manufacturer manufacturer = new Manufacturer(manuCode);
			
			String modelCode = rs.getString("model_code");
			String modelName = rs.getString("model_name");
			String modelDesc = rs.getString("model_desc");
			models.add(new Model(manufacturer, modelCode, modelName, modelDesc));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return models;
	}
	
	// Insert a new model into the model table
	public void addModel(Model newModel) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO model " +
					 									"VALUES (?,?,?,?)");
		
		myStmt.setString(1, newModel.getManufacturer().getManuCode());
		myStmt.setString(2, newModel.getModelCode());
		myStmt.setString(3, newModel.getModelName());
		myStmt.setString(4, newModel.getModelDesc());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
}
