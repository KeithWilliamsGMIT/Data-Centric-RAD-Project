package ie.gmit.rad;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ManufacturersController {
	private ArrayList<Manufacturer> manufacturers;
	private DAO dao;
	
	// Constructors
	public ManufacturersController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to get all the manufacturers from the database and store in an instance variable
	public void loadManufacturers() {
		try {
			manufacturers = dao.getManufacturers();
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to delete the given manufacturer from the database
	public String deleteManufacturer(Manufacturer manufacturer) {
		try {
			dao.deleteManufacturer(manufacturer);
			manufacturers.remove(manufacturer);
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Use the DAO to add the new manufacturer to the database
	public String addManufacturer(Manufacturer manufacturer) {
		try {
			dao.addManufacturer(manufacturer);
			return "list_manufacturers";
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Use the DAO to update a manufacturer in the database
	public String updateManufacturer(Manufacturer manufacturer) {
		try {
			dao.updateManufacturer(manufacturer);
			return "list_manufacturers";
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Add the selected manufacturer to ExternalContext 
	public String setUpdateManufacturer(Manufacturer manufacturer) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("manufacturer", manufacturer);
		
		return "update_manufacturer";
	}
	
	// Get the manufacturer from the ExternalContext
	public Manufacturer getUpdateManufacturer() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Manufacturer manufacturer = (Manufacturer)sessionMap.get("manufacturer");
		
		return manufacturer;
	}
	
	// Getters and Setters
	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}
}
