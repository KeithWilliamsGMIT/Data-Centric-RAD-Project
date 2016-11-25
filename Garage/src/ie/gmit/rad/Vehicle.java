package ie.gmit.rad;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Vehicle {
	private String vehicleReg;
	private String manuCode;
	private String modelCode;
	private int vehicleMileage;
	private float vehiclePrice;
	private String vehicleColour;
	private String vehicleFuel;
	
	public Vehicle() {}
	
	public Vehicle(String vehicleReg, String manuCode, String modelCode,
			int vehicleMileage, float vehiclePrice, String vehicleColour,
			String vehicleFuel) {
		super();
		this.vehicleReg = vehicleReg;
		this.manuCode = manuCode;
		this.modelCode = modelCode;
		this.vehicleMileage = vehicleMileage;
		this.vehiclePrice = vehiclePrice;
		this.vehicleColour = vehicleColour;
		this.vehicleFuel = vehicleFuel;
	}

	public String getVehicleReg() {
		return vehicleReg;
	}

	public void setVehicleReg(String vehicleReg) {
		this.vehicleReg = vehicleReg;
	}

	public String getManuCode() {
		return manuCode;
	}

	public void setManuCode(String manuCode) {
		this.manuCode = manuCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
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
