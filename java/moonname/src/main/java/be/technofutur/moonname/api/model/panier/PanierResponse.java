package be.technofutur.moonname.api.model.panier;

import be.technofutur.moonname.api.model.pierre.PierreResponse;
import be.technofutur.moonname.dl.entities.Pierre;
import java.util.List;

public record PanierResponse(List<PierreResponse> pierres) {
    public static PanierResponse from(List<Pierre> pierres) {
        return new PanierResponse(pierres.stream().map(PierreResponse::from).toList());
    }
}
