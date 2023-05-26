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
    public User getUserFromScanner() {
        Scanner scanner = new Scanner(System.in);
        return parseLine(scanner.nextLine());
    }

    @Override
    public void printAllUsers() {
        List<User> users = usersRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + " " + users.get(i).toString());
        }
    }

    @Override
    public boolean containsInRepository(User user) {
        List<User> users = usersRepository.findAll();
        return users.contains(user);
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
        double averageAge,
                ageSum = 0;
        for (User user : users) {
            ageSum = ageSum + user.getAge();
        }
        averageAge = ageSum / users.size();
        return averageAge;
    }

    @Override
    public int getAgeOfHighest() {
        List<User> users = usersRepository.findAll();
        int indexOfHighest = 0;
        for (int i = 1; i < users.size(); i++) {
            if (users.get(i).getHeight() > users.get(indexOfHighest).getHeight()) {
                indexOfHighest = i;
            }
        }
        return users.get(indexOfHighest).getAge();
    }

    @Override
    public List<User> getListWithRemovedUser(int i) {
        List<User> users = usersRepository.findAll();
        users.remove(users.get(i));
        return  users;
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
        return users.get(indexOfMinHeight).getFirstName() + " " + users.get(indexOfMinHeight).getLastName();
    }

    private static User parseLine(String line) {
        try {
            String[] parsed = line.split(" ");
            String firstName = parsed[0];
            String lastName = parsed[1];
            int age = Integer.parseInt(parsed[2]);
            double height = Double.parseDouble(parsed[3]);

            return new User(
                    firstName, lastName, age, height
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Неверный формат введенных данных!");
        }
        return null;
    }

}

