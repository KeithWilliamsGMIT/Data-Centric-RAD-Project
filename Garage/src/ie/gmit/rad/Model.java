package ie.gmit.rad;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Model {
	private String manuCode;
	private String modelCode;
	private String modelName;
	private String modelDesc;
	
	public Model() {}

	public Model(String manuCode, String modelCode, String modelName,
			String modelDesc) {
		super();
		this.manuCode = manuCode;
		this.modelCode = modelCode;
		this.modelName = modelName;
		this.modelDesc = modelDesc;
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
