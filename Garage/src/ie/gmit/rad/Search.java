package ie.gmit.rad;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Search {
	private String range;
	private Float price; // Use Float rather than the float so that it can be null
	private String colour;
	private String fuel;

	// Constructors
	public Search() { }
	
	// Getters and Setters
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
}
