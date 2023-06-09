package de.ait.services;

import de.ait.models.User;

import java.util.List;

public interface UsersService {
    List<String> getNames();
    User getUserFromList(int number);
    int  getUserListSize();
    String getLastNameOfMostAging();
    double getAverageUsersAge();
    String getFullNameOfMinHeight();
    int getAgeOfHighest();
    User getUserFromScanner();
    boolean containsInRepository(User user);
    void printAllUsers();
    List<User> getListWithRemovedUser(int number);
    List<User> getListWithRemovedUser(User user);
    boolean saveNewUser();
    boolean removeUserByNumber();
    boolean  removeUserByInput();
    void printAllNames();

    boolean changeAllUsersData( int userInMenuNumber);
    boolean changeRequiredUserData( int userInMenuNumber);
    List<User> getUsersByInputtedName();
    void getAndPrintUsersByInputtedName();


}
