package be.technofutur.moonname.api.controllers;

import be.technofutur.moonname.api.model.mission.MissionResponse;
import be.technofutur.moonname.bll.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    // Route publique pour afficher les missions dispo
    @GetMapping
    public List<MissionResponse> findAll() {
        return missionService.findAll().stream().map(MissionResponse::from).toList();
    }
}
