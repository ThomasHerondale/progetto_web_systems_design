package it.wsda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LedWallController {

    @GetMapping("/wsda")
    public String showLedWall(@RequestParam int id, Model model) {
        model.addAttribute("facilityId", id);

        return "led_wall";
    }
}