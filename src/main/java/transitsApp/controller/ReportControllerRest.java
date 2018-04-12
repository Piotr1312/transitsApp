package transitsApp.controller;

import java.time.LocalDate;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import transitsApp.logic.TransitsCalculator;
import transitsApp.model.DailyReport;
import transitsApp.model.PeriodicReport;

@RestController
@RequestMapping("/api/reports")
public class ReportControllerRest {
	
	private TransitsCalculator calc;
	
	@Autowired
	public ReportControllerRest(TransitsCalculator calc) {
		this.calc = calc;
	}
	
	@GetMapping(path = "/periodic")
	public PeriodicReport getPeriodicReport(@RequestParam(required = false) String start_date,
											@RequestParam(required = false) String end_date) {
		LocalDate startDate;
		LocalDate endDate;
		
		if(start_date == null || end_date == null) {
			endDate = LocalDate.now();
			Month month = endDate.getMonth();
			int year = endDate.getYear();
			startDate = LocalDate.of(year, month, 1);
		} else {
			startDate = LocalDate.parse(start_date);
			endDate = LocalDate.parse(end_date);
		}
		
		PeriodicReport report = calc.createPeriodicReport(startDate, endDate);
		
		return report;
	}
	
	@GetMapping(path = "/daily")
	public DailyReport getDailyReport(@RequestParam(required = false) String date) {
		LocalDate day;
		
		if(date == null)
			day = LocalDate.now();
		else
			day = LocalDate.parse(date);
		
		DailyReport report = calc.createDailyReport(day);
		
		return report;
	}
	
}
