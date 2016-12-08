package ie.gmit.rad;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Model {
	private Manufacturer manufacturer;
	private String modelCode;
	private String modelName;
	private String modelDesc;
	
	// Constructors
	public Model() {
		manufacturer = new Manufacturer();
	}

	public Model(Manufacturer manufacturer, String modelCode, String modelName,
			String modelDesc) {
		super();
		this.manufacturer = manufacturer;
		this.modelCode = modelCode;
		this.modelName = modelName;
		this.modelDesc = modelDesc;
	}
	
	// Getters and Setters
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelDesc() {
		return modelDesc;
	}

	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}
}
