package it.wsda.controller;

import it.wsda.dto.ScheduleDTO;
import it.wsda.services.SchedulesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@Controller
@RequestMapping("/schedules")
public class SchedulesController {

    private final SchedulesService schedulesService;

    public SchedulesController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    @GetMapping
    @ResponseBody
    public Collection<ScheduleDTO> getAllSchedules() {
        return schedulesService.getAllSchedules();
    }

    @PostMapping("/create")
    @ResponseBody
    public void createSchedule(@RequestBody ScheduleDTO scheduleDTO, HttpServletResponse response) {
        try {
            schedulesService.createSchedule(scheduleDTO);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    @GetMapping("/schedule")
    public ScheduleDTO getScheduleByFacilityId(@RequestParam int facilityId) {
        // Recupera il palinsesto associato all'impianto dato l'ID dell'impianto
        return schedulesService.getScheduleByFacilityId(facilityId);
    }
}