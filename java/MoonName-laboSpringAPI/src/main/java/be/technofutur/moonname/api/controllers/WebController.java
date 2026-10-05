package be.technofutur.moonname.api.controllers;

import be.technofutur.moonname.bll.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MissionService missionService;
    private final PierreService pierreService;

    // Petite page Thymeleaf juste pour montrer le front sans refaire une grosse appli
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("missions", missionService.findAll());
        model.addAttribute("pierres", pierreService.findAll());
        return "index";
    }
}
