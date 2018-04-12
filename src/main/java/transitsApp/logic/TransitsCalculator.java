package transitsApp.logic;

import java.time.LocalDate;
import java.util.List;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import transitsApp.logic.DistanceCalculator.Property;
import transitsApp.model.DailyReport;
import transitsApp.model.PeriodicReport;
import transitsApp.model.Transit;
import transitsApp.repository.TransitRepository;

@Component
public class TransitsCalculator {
	
private TransitRepository transitRepo;
private DistanceCalculator calc;
	
	@Autowired
	public TransitsCalculator(TransitRepository transitRepo, DistanceCalculator calc) {
		this.transitRepo = transitRepo;
		this.calc = calc;
	}
	
	public void calculateTransit(Transit transit) {
		String source = transit.getSourceAddress();
		String destination = transit.getDestinationAddress();
		int distanceMetres = calc.getProperty(source, destination, Property.DISTANCE);
		double time = calc.getProperty(source, destination, Property.DURATION);
		double fuelBurnt = distanceMetres * transit.getAvgFuelConsumption() / 100000;
		double fuelCost = fuelBurnt * transit.getFuelCostPerLitre();
		
		transit.setDistance(distanceMetres / 1000.0);
		transit.setTime(time);
		transit.setFuelBurnt(fuelBurnt);
		transit.setFuelCost(fuelCost);
	}
	
	public DailyReport createDailyReport(LocalDate date) {
		List<Transit> transits = transitRepo.findByDate(date);
		double totalDistance = getTotalDistance(transits);
		double totalTime = getTotalTime(transits);
		double totalFuelBurnt = getTotalFuelBurnt(transits);
		double totalFuelCost = getTotalFuelCost(transits);
		
		DailyReport dailyReport = new DailyReport();
		dailyReport.setDate(date);
		dailyReport.setTotalDistance(totalDistance);
		dailyReport.setTotalFuelBurnt(totalFuelBurnt);
		dailyReport.setTotalFuelCost(totalFuelCost);
		dailyReport.setTotalTime(totalTime);
		
		return dailyReport;
	}
	
	public PeriodicReport createPeriodicReport(LocalDate startDate, LocalDate endDate) {
		List<Transit> transits = transitRepo.findByDateBetween(startDate, endDate);
		double totalDistance = getTotalDistance(transits);
		double totalTime = getTotalTime(transits);
		double totalFuelBurnt = getTotalFuelBurnt(transits);
		double totalFuelCost = getTotalFuelCost(transits);
		
		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		
		double avgDailyDistance = totalDistance / daysBetween;
		double avgDailyTime = totalTime / daysBetween;
		double avgDailyFuelBurnt = totalFuelBurnt / daysBetween;
		double avgDailyFuelCost = totalFuelCost / daysBetween;
		
		PeriodicReport periodicReport = new PeriodicReport();
		periodicReport.setStartDate(startDate);
		periodicReport.setEndDate(endDate);
		periodicReport.setTotalDistance(totalDistance);
		periodicReport.setTotalFuelBurnt(totalFuelBurnt);
		periodicReport.setTotalFuelCost(totalFuelCost);
		periodicReport.setTotalTime(totalTime);
		periodicReport.setAvgDailyDistance(avgDailyDistance);
		periodicReport.setAvgDailyFuelBurnt(avgDailyFuelBurnt);
		periodicReport.setAvgDailyFuelCost(avgDailyFuelCost);
		periodicReport.setAvgDailyTime(avgDailyTime);
		
		return periodicReport;
	}
	
	private double getTotalDistance(List<Transit> transits) {
		double totalDistance = 0;
		
		for(Transit transit : transits)
			totalDistance += transit.getDistance();
		
		return totalDistance;
	}
	
	private double getTotalTime(List<Transit> transits) {
		double totalTime = 0;
		
		for(Transit transit : transits)
			totalTime += transit.getTime();
		
		return totalTime;
	}
	
	private double getTotalFuelBurnt(List<Transit> transits) {
		double totalFuelBurnt = 0;
		
		for(Transit transit : transits)
			totalFuelBurnt += transit.getFuelBurnt();
		
		return totalFuelBurnt;
	}
	
	private double getTotalFuelCost(List<Transit> transits) {
		double totalFuelCost = 0;
		
		for(Transit transit : transits)
			totalFuelCost += transit.getFuelCost();
		
		return totalFuelCost;
	}

}