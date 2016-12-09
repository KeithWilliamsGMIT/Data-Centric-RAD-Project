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
	
	// Update the manufacturer in the manufacturer table with the code of the given manufacturer
	public void updateManufacturer(Manufacturer manufacturer) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("UPDATE manufacturer " +
					 									"SET manu_name=?, " +
					 									"manu_details=? " +
					 									"WHERE manu_code=?;");
		
		myStmt.setString(1, manufacturer.getManuName());
		myStmt.setString(2, manufacturer.getManuDetails());
		myStmt.setString(3, manufacturer.getManuCode());
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
	
	// Return an array list of all vehicles from the database
	public ArrayList<Vehicle> getVehicles() throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM manufacturer ma " +
														"INNER JOIN model mo " +
														"ON ma.manu_code = mo.manu_code " +
														"INNER JOIN vehicle v " +
														"ON mo.model_code = v.model_code;");
		
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			String manuCode = rs.getString("manu_code");
			String manuName = rs.getString("manu_name");
			String manuDetails = rs.getString("manu_details");
			Manufacturer manufacturer = new Manufacturer(manuCode, manuName, manuDetails);
			
			String modelCode = rs.getString("model_code");
			String modelName = rs.getString("model_name");
			String manuDesc = rs.getString("model_desc");
			Model model = new Model(manufacturer, modelCode, modelName, manuDesc);
			
			String reg = rs.getString("reg");
			int mileage = rs.getInt("mileage");
			float price = rs.getFloat("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");
			vehicles.add(new Vehicle(reg, model, mileage, price, colour, fuel));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return vehicles;
	}
	
	// Insert a new vehicle into the vehicle table
	public void addVehicle(Vehicle newVehicle) throws Exception {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO vehicle " +
					 									"VALUES (?,?,?,?,?,?,?)");
		
		myStmt.setString(1, newVehicle.getVehicleReg());
		myStmt.setString(2, newVehicle.getModel().getManufacturer().getManuCode());
		myStmt.setString(3, newVehicle.getModel().getModelCode());
		myStmt.setInt(4, newVehicle.getVehicleMileage());
		myStmt.setFloat(5, newVehicle.getVehiclePrice());
		myStmt.setString(6, newVehicle.getVehicleColour());
		myStmt.setString(7, newVehicle.getVehicleFuel());
		myStmt.executeUpdate();
		
		myStmt.close();
		conn.close();
	}
	
	// Return an array list of all vehicles from the database that match the search
	public ArrayList<Vehicle> searchVehicles(Search search) throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Connection conn = mysqlDS.getConnection();
		
		// Build the query
		String query = "SELECT * FROM manufacturer ma " +
						"INNER JOIN model mo " +
						"ON ma.manu_code = mo.manu_code " +
						"INNER JOIN vehicle v " +
						"ON mo.model_code = v.model_code " +
						"WHERE ";
		
		if (search.getPrice() != null) {
			query += "v.price " + search.getRange() + " ? AND ";
		}
		
		if (!search.getColour().isEmpty()) {
			query += "v.colour = ? AND ";
		}
		
		query += "v.fuel = ?;";
		
		PreparedStatement myStmt = conn.prepareStatement(query);
		
		// Set parameters
		int index = 1;
		
		if (search.getPrice() != null) {
			myStmt.setFloat(index++, search.getPrice());
		}
		
		if (!search.getColour().isEmpty()) {
			myStmt.setString(index++, search.getColour());
		}
		
		myStmt.setString(index, search.getFuel());
		
		ResultSet rs = myStmt.executeQuery();
		
		while (rs.next()) {
			String manuCode = rs.getString("manu_code");
			String manuName = rs.getString("manu_name");
			String manuDetails = rs.getString("manu_details");
			Manufacturer manufacturer = new Manufacturer(manuCode, manuName, manuDetails);
			
			String modelCode = rs.getString("model_code");
			String modelName = rs.getString("model_name");
			String manuDesc = rs.getString("model_desc");
			Model model = new Model(manufacturer, modelCode, modelName, manuDesc);
			
			String reg = rs.getString("reg");
			int mileage = rs.getInt("mileage");
			float price = rs.getFloat("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");
			vehicles.add(new Vehicle(reg, model, mileage, price, colour, fuel));
		}
		
		myStmt.close();
		rs.close();
		conn.close();
		
		return vehicles;
	}
}
