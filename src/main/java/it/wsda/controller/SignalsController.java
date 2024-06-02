package it.wsda.controller;

import it.wsda.services.SignalsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignalsController {
    private final SignalsService signalsService;

    public SignalsController(SignalsService signalsService) {
        this.signalsService = signalsService;
    }

    @GetMapping("/report")
    public String report(Model model) {
        var advScreenTimes = signalsService.getAdvScreenTimes();
        model.addAttribute("advScreenTimes", advScreenTimes);
        return "report";
    }
}
