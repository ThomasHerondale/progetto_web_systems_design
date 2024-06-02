package it.wsda.controller;

import it.wsda.services.SignalsService;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class SignalsController {
    private final SignalsService signalsService;

    public SignalsController(SignalsService signalsService) {
        this.signalsService = signalsService;
    }

    @GetMapping({"/report"})
    public String report(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                             Optional<Date> startDate,
                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                             Optional<Date> endDate,
                         Model model) {
        Map<String, Integer> advScreenTimes;
        if (startDate.isPresent() && endDate.isPresent())
            advScreenTimes = signalsService.getAdvScreenTimes(startDate.get(), endDate.get());
        else
            advScreenTimes = signalsService.getAdvScreenTimes();
        model.addAttribute("advScreenTimes", advScreenTimes);

        return "report";
    }
}
