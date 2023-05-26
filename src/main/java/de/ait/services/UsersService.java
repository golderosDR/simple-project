package de.ait.services;

import de.ait.models.User;

import java.util.List;

public interface UsersService {
    List<String> getNames();

    String getLastNameOfMostAging();
    double getAverageUsersAge();
    String getFullNameOfMinHeight();
    int getAgeOfHighest();
    User getUserFromScanner();
    boolean containsInRepository(User user);
    void printAllUsers();
    List<User> getListWithRemovedUser(int i);
}
