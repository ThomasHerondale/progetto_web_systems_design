package it.wsda.controller;


import it.wsda.dto.FacilityDTO;
import it.wsda.services.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
@RequestMapping("/facilities")
public class FacilitiesController {

    @Autowired
    private FacilitiesService facilitiesService;

    @GetMapping
    @ResponseBody
    public Collection<FacilityDTO> getAllFacilities() {
        return facilitiesService.getAllFacilities();
    }

    @PostMapping("/create")
    @ResponseBody
    public void createFacility(@RequestBody FacilityDTO facilityDTO, HttpServletResponse response) {
        try {
            facilitiesService.createFacility(facilityDTO);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public void updateFacility(@RequestBody FacilityDTO facilityDTO, HttpServletResponse response) {
        try {
            facilitiesService.updateFacility(facilityDTO);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
