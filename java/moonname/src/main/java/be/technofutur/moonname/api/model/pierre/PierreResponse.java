package be.technofutur.moonname.api.model.pierre;

import be.technofutur.moonname.dl.entities.Pierre;

public record PierreResponse(
        Integer id,
        String inscription,
        String pays,
        String statut,
        String mission,
        String client
) {
    public static PierreResponse from(Pierre pierre) {
        return new PierreResponse(
                pierre.getId(), pierre.getInscription(), pierre.getPays(), pierre.getStatut().name(),
                pierre.getMission().getNom(), pierre.getClient().getUsername()
        );
    }
}
