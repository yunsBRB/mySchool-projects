package be.technofutur.moonname.api.model.user;

public record TokenResponse(String username, String role, String accessToken, String refreshToken) { }
