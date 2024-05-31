package it.wsda.controller;


import it.wsda.services.FacilitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ManagerController {

    private final FacilitiesService facilitiesService;

    public ManagerController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }

    @GetMapping("/manager")
    public String home(Model model) {
        var facilities = facilitiesService.getAllFacilities();
        model.addAttribute("facilities", facilities);
        return "manager";
    }
}
