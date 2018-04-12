package transitsApp.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transit {
	
	@Id
	@GeneratedValue
	private Long id;
	private String sourceAddress;
	private String destinationAddress;
	private Double avgFuelConsumption;
	private Double fuelCostPerLitre;
	private LocalDate date;
	private Double distance;
	private Double time;
	private Double fuelBurnt;
	private Double fuelCost;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSourceAddress() {
		return sourceAddress;
	}
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public Double getAvgFuelConsumption() {
		return avgFuelConsumption;
	}
	public void setAvgFuelConsumption(Double avgFuelConsumption) {
		this.avgFuelConsumption = avgFuelConsumption;
	}
	public Double getFuelCostPerLitre() {
		return fuelCostPerLitre;
	}
	public void setFuelCostPerLitre(Double fuelCostPerLitre) {
		this.fuelCostPerLitre = fuelCostPerLitre;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	public Double getFuelBurnt() {
		return fuelBurnt;
	}
	public void setFuelBurnt(Double fuelBurnt) {
		this.fuelBurnt = fuelBurnt;
	}
	public Double getFuelCost() {
		return fuelCost;
	}
	public void setFuelCost(Double fuelCost) {
		this.fuelCost = fuelCost;
	}

}
