package it.wsda.controller;


import it.wsda.converters.FacilityDTOEditor;
import it.wsda.dto.FacilityDTO;
import it.wsda.services.FacilitiesService;
import it.wsda.services.SchedulesService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/facilities")
public class FacilitiesController {

    private final FacilitiesService facilitiesService;
    private final SchedulesService schedulesService;

    public FacilitiesController(FacilitiesService facilitiesService, SchedulesService schedulesService) {
        this.facilitiesService = facilitiesService;
        this.schedulesService = schedulesService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FacilityDTO.class, new FacilityDTOEditor());
    }

    @GetMapping
    @ResponseBody
    public Collection<FacilityDTO> getAllFacilities() {
        return facilitiesService.getAllFacilities();
    }

    @GetMapping("/create")
    public String createFacility(Model model) {
        model.addAttribute("facility", new FacilityDTO());
        model.addAttribute("schedules", schedulesService.getAllSchedules());
        return "createFacility";
    }

    @PostMapping("/create")
    public String createFacility(@ModelAttribute FacilityDTO facility, Model model) {
        try {
            facilitiesService.createFacility(facility);
            return "manager";
        } catch (Exception e) {
            return "errors/generic";
        }
    }

    @GetMapping("/update/{id}")
    public String updateFacility(@PathVariable(value = "id") int id, Model model) {
        var facility = facilitiesService.findFacilityById(id);
        var formModel = new UpdateFacilityFormModel(facility, false);
        System.out.println(formModel);
        model.addAttribute("formModel", formModel);

        var schedule = schedulesService.getAllSchedules();
        model.addAttribute("schedules", schedule);

        return "updateFacility";
    }

    @PostMapping("/update")
    public String updateFacility(@ModelAttribute UpdateFacilityFormModel formModel) {
        var facility = formModel.facility;

        if (formModel.toggleStatus) {
            var newStatus = facility.getStatus().equals("ACTIVE") ? "INACTIVE" : "ACTIVE";
            facility.setStatus(newStatus);
        }

        // query only if schedule was updated
        if (!facility.getSchedule().getId().equals(formModel.scheduleId)) {
            var schedule = schedulesService.getScheduleById(formModel.scheduleId);
            facility.setSchedule(schedule);
        }

        facilitiesService.updateFacility(facility);

        return "redirect:../manager";
    }

    @Data
    public static class UpdateFacilityFormModel {
        private FacilityDTO facility;
        private boolean toggleStatus;
        private String scheduleId;

        public UpdateFacilityFormModel(FacilityDTO facility, boolean toggleStatus) {
            this.facility = facility;
            this.scheduleId = facility.getSchedule().getId();
            this.toggleStatus = toggleStatus;
        }
    }

}
