package transitsApp.model;

import java.time.LocalDate;

public class DailyReport {
    private LocalDate date;
    private double totalDistance;
    private double totalFuelBurnt;
    private double totalFuelCost;
    private double totalTime;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

}
