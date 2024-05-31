package it.wsda.controller;


import it.wsda.entity.Facility;
import it.wsda.entity.Signal;
import it.wsda.services.FacilitiesService;
import it.wsda.services.SchedulesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ManagerController {

    private final FacilitiesService facilitiesService;
    private final SchedulesService schedulesService;

    public ManagerController(FacilitiesService facilitiesService, SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
        this.facilitiesService = facilitiesService;
    }

    @GetMapping("/manager")
    public String home(Model model) {
        var facilities = facilitiesService.getAllFacilities();
        model.addAttribute("facilities", facilities);

        var schedules = schedulesService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "manager";
    }

    @PostMapping("update")
    public String updateFacility() {

    }

}
