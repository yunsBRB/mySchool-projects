package be.technofutur.moonname.bll.services;

import be.technofutur.moonname.dl.entities.User;

public interface AuthService {
    User login(String username, String password);
    User findByUsername(String username);
}
