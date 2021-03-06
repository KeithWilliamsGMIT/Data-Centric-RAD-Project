package ie.gmit.rad;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Vehicle {
	private String vehicleReg;
	private Model model;
	private int vehicleMileage;
	private float vehiclePrice;
	private String vehicleColour;
	private String vehicleFuel;
	
	// Constructors
	public Vehicle() {
		model = new Model();
	}
	
	public Vehicle(String vehicleReg, Model model, int vehicleMileage,
			float vehiclePrice, String vehicleColour, String vehicleFuel) {
		super();
		this.vehicleReg = vehicleReg;
		this.model = model;
		this.vehicleMileage = vehicleMileage;
		this.vehiclePrice = vehiclePrice;
		this.vehicleColour = vehicleColour;
		this.vehicleFuel = vehicleFuel;
	}
	
	// Getters and Setters
	public String getVehicleReg() {
		return vehicleReg;
	}

	public void setVehicleReg(String vehicleReg) {
		this.vehicleReg = vehicleReg;
	}

	public Model getModel() {
		return model;
	}

	public void setModelCode(Model model) {
		this.model = model;
	}

	public int getVehicleMileage() {
		return vehicleMileage;
	}

	public void setVehicleMileage(int vehicleMileage) {
		this.vehicleMileage = vehicleMileage;
	}

	public float getVehiclePrice() {
		return vehiclePrice;
	}

	public void setVehiclePrice(float vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}

	public String getVehicleColour() {
		return vehicleColour;
	}

	public void setVehicleColour(String vehicleColour) {
		this.vehicleColour = vehicleColour;
	}

	public String getVehicleFuel() {
		return vehicleFuel;
	}

	public void setVehicleFuel(String vehicleFuel) {
		this.vehicleFuel = vehicleFuel;
	}
}
