package transitsApp.model;

import java.time.LocalDate;

public class PeriodicReport {
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalDistance;
    private double totalFuelBurnt;
    private double totalFuelCost;
    private double totalTime;
    private double avgDailyDistance;
    private double avgDailyFuelBurnt;
    private double avgDailyFuelCost;
    private double avgDailyTime;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalFuelBurnt() {
        return totalFuelBurnt;
    }

    public void setTotalFuelBurnt(double totalFuelBurnt) {
        this.totalFuelBurnt = totalFuelBurnt;
    }

    public double getTotalFuelCost() {
        return totalFuelCost;
    }

    public void setTotalFuelCost(double totalFuelCost) {
        this.totalFuelCost = totalFuelCost;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getAvgDailyDistance() {
        return avgDailyDistance;
    }

    public void setAvgDailyDistance(double avgDailyDistance) {
        this.avgDailyDistance = avgDailyDistance;
    }

    public double getAvgDailyFuelBurnt() {
        return avgDailyFuelBurnt;
    }

    public void setAvgDailyFuelBurnt(double avgDailyFuelBurnt) {
        this.avgDailyFuelBurnt = avgDailyFuelBurnt;
    }

    public double getAvgDailyFuelCost() {
        return avgDailyFuelCost;
    }

    public void setAvgDailyFuelCost(double avgDailyFuelCost) {
        this.avgDailyFuelCost = avgDailyFuelCost;
    }

    public double getAvgDailyTime() {
        return avgDailyTime;
    }

    public void setAvgDailyTime(double avgDailyTime) {
        this.avgDailyTime = avgDailyTime;
    }

}
