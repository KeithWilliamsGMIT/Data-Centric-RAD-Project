package ie.gmit.rad;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ManufacturersController {
	private ArrayList<Manufacturer> manufacturers;
	private Manufacturer newManufacturer = new Manufacturer();
	private DAO dao;
	
	public ManufacturersController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Get all the manufacturers from the database and store in an instance variable
	// Delegate the work to the DAO
	public void loadManufacturers() {
		try {
			manufacturers = dao.getManufacturers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Add the new manufacturer to the database
	// Delegate the work to the DAO
	public String addManufacturer() {
		try {
			dao.addManufacturer(newManufacturer);
			return "list_manufacturers";
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Getters and Setters
	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public Manufacturer getNewManufacturer() {
		return newManufacturer;
	}

	public void setNewManufacturer(Manufacturer newManufacturer) {
		this.newManufacturer = newManufacturer;
	}
}
