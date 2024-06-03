package it.wsda.controller;

import it.wsda.services.FacilitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LedWallController {

    private final FacilitiesService facilitiesService;

    public LedWallController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }

    @GetMapping("/wsda")
    public String showLedWall(@RequestParam int id, Model model) {
        model.addAttribute("facilityId", id);

        var facility = facilitiesService.findFacilityById(id);
        if (facility.getStatus().equals("INACTIVE"))
            return "inactiveFacility";
        else
            return "led_wall";
    }
}