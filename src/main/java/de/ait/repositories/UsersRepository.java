package de.ait.repositories;

import de.ait.models.User;

import java.util.List;

public interface UsersRepository {
    List<User> findAll();
    boolean addToFile(User user);
    boolean rewriteFile(List<User> users);
}
