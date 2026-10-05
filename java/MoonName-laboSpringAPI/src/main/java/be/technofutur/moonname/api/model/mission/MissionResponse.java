package be.technofutur.moonname.api.model.mission;

import be.technofutur.moonname.dl.entities.Mission;
import java.time.LocalDate;

public record MissionResponse(Integer id, String nom, LocalDate dateDepart) {
    public static MissionResponse from(Mission mission) {
        return new MissionResponse(mission.getId(), mission.getNom(), mission.getDateDepart());
    }
}
