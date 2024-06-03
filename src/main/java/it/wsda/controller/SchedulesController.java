package it.wsda.controller;

import it.wsda.WebSystemsDesignProjectApplication;
import it.wsda.dto.ScheduleDTO;
import it.wsda.services.SchedulesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    @ResponseBody
    public ScheduleDTO getScheduleByFacilityId(@RequestParam Integer facilityId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        // Recupera il palinsesto associato all'impianto dato l'ID dell'impianto
        return schedulesService.getScheduleByFacilityId(facilityId);
    }

    @GetMapping("/file")
    @ResponseBody
    public String getScheduleFileResource(@RequestParam String file_path, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        var res = new ClassPathResource("/static/" + file_path, WebSystemsDesignProjectApplication.class);
        try (var inputStream = res.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}