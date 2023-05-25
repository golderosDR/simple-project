package de.ait.services;

import de.ait.models.User;
import de.ait.repositories.UsersRepository;

import java.util.*;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<String> getNames() {
        List<User> users = usersRepository.findAll();
        List<String> names = new ArrayList<>();

        for (User user : users) {
            names.add(user.getFirstName());
        }
        return names;
    }

    @Override
    public String getLastNameOfMostAging() {
        List<User> users = usersRepository.findAll();
        Map<Integer, String> userAge = new HashMap<>();

        for (User user : users) {
            userAge.put(user.getAge(), user.getLastName());
        }
        return userAge.get(Collections.max(userAge.keySet()));
    }

    @Override
    public double getAverageUsersAge() {
        List<User> users = usersRepository.findAll();
        double averageAge = 0;
        double ageSum = 0;
        for (User user : users) {
            ageSum = ageSum + user.getAge();
        }
        averageAge = ageSum / users.size();
        return averageAge;
    }

    @Override
    public String getFullNameOfMinHeight() {
        List<User> users = usersRepository.findAll();
        int indexOfMinHeight = 0;
        for (int i = 1; i < users.size(); i++) {
            if (users.get(i).getHeight() < users.get(indexOfMinHeight).getHeight()) {
                indexOfMinHeight = i;
            }

        }
        String fullNameOfMinHeight = users.get(indexOfMinHeight).getFirstName() + users.get(indexOfMinHeight).getLastName();
        return fullNameOfMinHeight;
    }
}

