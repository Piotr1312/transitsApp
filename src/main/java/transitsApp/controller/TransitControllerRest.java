package transitsApp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import transitsApp.logic.TransitCalculator;
import transitsApp.model.Transit;
import transitsApp.repository.TransitRepository;

@RestController
@RequestMapping("/api/transits")
public class TransitControllerRest {

    private TransitCalculator calc;
    private TransitRepository transitRepo;

    @Autowired
    public TransitControllerRest(TransitRepository transitRepo, TransitCalculator calc) {
        this.transitRepo = transitRepo;
        this.calc = calc;
    }

    @GetMapping(path = "/last10")
    public List<Transit> getTransit() {
        List<Transit> transits = transitRepo.findFirst10ByOrderByIdDesc();
        return transits;
    }

    @GetMapping(path = "/periodic")
    public List<Transit> getPeriodicTransits(@RequestParam String start_date,
                                             @RequestParam String end_date) {
        LocalDate startDate = LocalDate.parse(start_date);
        LocalDate endDate = LocalDate.parse(end_date);
        List<Transit> transits = transitRepo.findByDateBetween(startDate, endDate);

        return transits;
    }

    @PostMapping
    public void saveTransit(@RequestBody Transit transit) {
        calc.calculateTransit(transit);
        transitRepo.save(transit);
    }

}
