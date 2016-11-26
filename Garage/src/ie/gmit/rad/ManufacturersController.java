package ie.gmit.rad;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ManufacturersController {
	private ArrayList<Manufacturer> manufacturers;
	private DAO dao;
	
	public ManufacturersController() {}
	
	// Get all the manufacturers from the database and store in an instance variable
	// Create a DAO object and delegate the work to it
	public void loadManufacturers() {
		try {
			dao = new DAO();
			manufacturers = dao.getManufacturers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Getters and Setters
	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}
}
