package be.technofutur.moonname.api.model.pierre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PierreRequest(
        @NotBlank @Size(max = 40) String inscription,
        @NotBlank @Size(max = 40) String pays,
        @NotNull Integer missionId
) { }
