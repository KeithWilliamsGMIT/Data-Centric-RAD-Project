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
public class VehiclesController {
	private ArrayList<Vehicle> vehicles;
	private DAO dao;
	
    // Constructors
	public VehiclesController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to get all the vehicles from the database and store them in an instance variable
	public void loadVehicles() {
		try {
			vehicles = dao.getVehicles();
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to add the new vehicle to the database
	public String addVehicle(Vehicle vehicle) {
		try {
			dao.addVehicle(vehicle);
			return "list_vehicles";
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Add the selected vehicle to ExternalContext 
	public String allDetails(Vehicle vehicle) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("vehicle", vehicle);
		
		return "vehicle_details";
	}
	
	// Get the vehicle from the external context
	public Vehicle getVehicle() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> sessionMap = externalContext.getSessionMap();
		Vehicle vehicle = (Vehicle)sessionMap.get("vehicle");
		
		return vehicle;
	}
	
	// Getters and Setters
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
}