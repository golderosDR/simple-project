package de.ait.services;

import de.ait.messages.ErrorMessages;
import de.ait.models.Menu;
import de.ait.models.User;
import de.ait.repositories.UsersRepository;

import java.util.*;

public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

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
    public int getUserListSize() {
        return usersRepository.findAll().size();
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
        System.out.println();
    }

    @Override
    public boolean containsInRepository(User user) {
        List<User> users = usersRepository.findAll();
        return users.contains(user);
    }

    @Override
    public void saveNewUser() {
        System.out.println("Введите данные нового пользователя в формате 'Имя Фамилия возраст рост':");
        User newUser = getUserFromScanner();
        if (newUser != null) {
            if (!containsInRepository(newUser)) {
                if (usersRepository.addToFile(newUser)) {
                    System.out.printf("Пользователь %s успешно добавлен.%n", newUser);
                }
            } else {
                System.out.printf("Пользователь %s уже существует.%n", newUser);
            }
        } else {
            System.err.println(ErrorMessages.UNABLE_TO_ADD_USER_ERROR);
        }
    }

    @Override
    public void removeUserByNumber() {
        Scanner scanner = new Scanner(System.in);
        printAllUsers();
        System.out.println("Введите номер пользователя из списка для удаления: ");
        String temp = scanner.next();
        if (!temp.matches("[-+]?\\d+")) {
            Menu.wrongCommand();
        } else {
            int userInMenuNumber = Integer.parseInt(temp);
            if (getUserListSize() >= userInMenuNumber) {
                List<User> newUsersList = getListWithRemovedUser(userInMenuNumber - 1);
                if (usersRepository.rewriteFile(newUsersList)) {
                    System.out.println("Пользователь успешно удален из списка.");
                } else {
                    System.err.println(ErrorMessages.UNABLE_TO_REMOVE_USER_ERROR);
                }
            } else {
                Menu.wrongCommand();
            }
        }
    }

    @Override
    public void removeUserByInput() {
        System.out.println("Введите данные пользователя в формате 'Имя Фамилия возраст рост':");
        User userToRemove = getUserFromScanner();
        if (userToRemove != null) {
            if (containsInRepository(userToRemove)) {
                List<User> newUsersList = getListWithRemovedUser(userToRemove);
                if (usersRepository.rewriteFile(newUsersList)) {
                    System.out.println("Пользователь успешно удален из списка.");
                } else {
                    System.err.println(ErrorMessages.UNABLE_TO_REMOVE_USER_ERROR);
                }
            } else {
                System.out.printf("Пользователь %s отсутствует в базе.%n", userToRemove);
            }
        }
    }

    @Override
    public void changeUserData() {
        Scanner scanner = new Scanner(System.in);
        printAllUsers();
        System.out.println("Введите номер пользователя из списка для изменения значения параметров: ");
        String temp = scanner.next();
        if (!temp.matches("[-+]?\\d+")) {
            Menu.wrongCommand();
        } else {
            int userInMenuNumber = Integer.parseInt(temp);
            if (getUserListSize() >= userInMenuNumber) {
                User userToChange = getUserFromList(userInMenuNumber - 1);
                Menu.showChangeDataSubmenu();

                switch (scanner.next()) {
                    case "1" -> changeAllUsersData(userToChange, userInMenuNumber);

                    case "2" -> changeRequiredUserData(userToChange, userInMenuNumber);

                    case "0" -> Menu.menuCancel();

                    default -> Menu.wrongCommand();
                }
            } else {
                Menu.wrongCommand();
            }
        }
    }

    @Override
    public void printAllNames() {
        System.out.println("Имена пользователей:");
        for (String name : getNames()) {
            System.out.println(name);
        }
        System.out.println();
    }

    private void changeAllUsersData(User userToChange, int userInMenuNumber) {
        System.out.println(userToChange);
        System.out.println("Введите данные нового пользователя в формате 'Имя Фамилия возраст рост':");
        User newUser = getUserFromScanner();
        if (newUser != null) {
            if (!containsInRepository(newUser) && !userToChange.equals(newUser)) {

                List<User> newUserList = usersRepository.findAll();
                newUserList.set(userInMenuNumber - 1, newUser);
                if (usersRepository.rewriteFile(newUserList)) {
                    System.out.println("Данные успешно перезаписаны");
                    System.out.println();
                }

            } else {
                System.out.printf("Пользователь %s уже существует.%n", newUser);
            }
        } else {
            System.err.println(ErrorMessages.UNABLE_TO_ADD_USER_ERROR);
        }
    }

    private void changeRequiredUserData(User userToChange, int userInMenuNumber) {
        Scanner scanner = new Scanner(System.in);
        User newUser = new User(
                userToChange.getFirstName(),
                userToChange.getLastName(),
                userToChange.getAge(),
                userToChange.getHeight());

        Menu.showChoseParameterSubmenu();
        switch (scanner.next()) {
            case "1" -> {
                System.out.println("Введите новое имя пользователя");
                String newFirstName = scanner.next();
                if (newFirstName.length() < 1) {
                    System.err.println(ErrorMessages.INVALID_INPUT_VALUE_ERROR);
                    return;
                } else {
                    newUser.setFirstName(newFirstName);
                }
            }
            case "2" -> {
                System.out.println("Введите новую фамилию пользователя");
                String newLastName = scanner.next();
                if (newLastName.length() < 1) {
                    System.err.println(ErrorMessages.INVALID_INPUT_VALUE_ERROR);
                    return;
                } else {
                    newUser.setLastName(newLastName);
                }
            }
            case "3" -> {
                System.out.println("Введите новый возраст пользователя");
                String temp = scanner.next();
                int newAge;
                try {
                    newAge = Integer.parseInt(temp);
                } catch (RuntimeException e) {
                    System.err.println(ErrorMessages.WRONG_DATA_FORMAT_ERROR);
                    return;
                }
                if (newAge < 0) {
                    System.err.println(ErrorMessages.INVALID_INPUT_VALUE_ERROR);
                    return;
                } else {
                    newUser.setAge(newAge);
                }
            }
            case "4" -> {
                System.out.println("Введите новый рост пользователя");
                String temp = scanner.next();
                double newHeight;
                try {
                    newHeight = Double.parseDouble(temp);
                } catch (RuntimeException e) {
                    System.err.println(ErrorMessages.WRONG_DATA_FORMAT_ERROR);
                    return;
                }
                if (newHeight < 0) {
                    System.err.println(ErrorMessages.INVALID_INPUT_VALUE_ERROR);
                    return;
                } else {
                    newUser.setHeight(newHeight);
                }
            }
            case "0" -> {
                Menu.menuCancel();
                return;
            }
            default -> {
                Menu.wrongCommand();
                return;
            }
        }
        if (!containsInRepository(newUser) && !userToChange.equals(newUser)) {

            List<User> newUserList = usersRepository.findAll();
            newUserList.set(userInMenuNumber - 1, newUser);
            if (usersRepository.rewriteFile(newUserList)) {
                System.out.println("Данные успешно перезаписаны");
                System.out.println();
            }

        } else {
            System.out.printf("Пользователь %s уже существует.%n", newUser);
        }

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
        return users;
    }
    public List<User> getListWithRemovedUser(User user) {
        List<User> users = usersRepository.findAll();
        users.remove(user);
        return users;
    }

        @Override
    public User getUserFromList(int number) {
        List<User> users = usersRepository.findAll();
        return users.get(number);
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

            return new User(firstName, lastName, age, height);

        } catch (RuntimeException e) {
            System.err.println(ErrorMessages.WRONG_DATA_FORMAT_ERROR);
        }
        return null;
    }

}

