package ie.gmit.rad;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ModelsController {
	private ArrayList<Model> models;
	private DAO dao;
	
	// Constructors
	public ModelsController() {
		// Create the DAO object
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to get all the models from the database and store them in an instance variable
	public void loadModels() {
		try {
			models = dao.getModels();
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Use the DAO to add the new model to the database
	public String addModel(Model model) {
		try {
			dao.addModel(model);
			return "list_models";
		} catch (SQLException se) {
			FacesMessage message = new FacesMessage("ERROR: " + se.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Getters and Setters
	public ArrayList<Model> getModels() {
		return models;
	}
}
