package de.ait.app;

import de.ait.models.User;
import de.ait.repositories.UsersRepository;
import de.ait.repositories.UsersRepositoryListImpl;
import de.ait.repositories.UsersRepositoryTextFileImpl;
import de.ait.services.UsersService;
import de.ait.services.UsersServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести имена всех пользователей.
            2. Сохранить нового пользователя.
            3. Удалить пользователя.
            4. Вывести фамилию самого взрослого пользователя.
            5. Вывести средний возраст всех пользователей.
            6. Вывести возраст самого высокого пользователя.
            7. Вывести имя и фамилию самого низкого пользователя.

            0. Выход.
            """;
    private final static String REMOVE_SUBMENU_TEXT = """
           1. Удалить пользователя из списка пользователей по номеру.
           2. Удалить пользователя по введенным данным.
           0. Отмена.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersRepository usersRepository = new UsersRepositoryTextFileImpl("users.txt");
        UsersRepository testUserRepository = new UsersRepositoryListImpl();
        UsersService usersService = new UsersServiceImpl(usersRepository);

        while (true) {
            System.out.println(MAIN_MENU_TEXT);

            String command = scanner.next();

            switch (command) {
                case "1" -> {
                    System.out.println("Имена пользователей:");
                    List<String> names = usersService.getNames();
                    for (String name : names) {
                        System.out.println(name);
                    }
                    System.out.println();
                }
                case "2" -> {
                    System.out.println("Введите данные нового пользователя в формате 'Имя Фамилия возраст рост':");
                    scanner.nextLine();
                    User newUser = usersService.getUserFromScanner();
                    if (newUser != null) {
                        if (!usersService.containsInRepository(newUser)) {
                            if (usersRepository.addToFile(newUser)) {
                                System.out.printf("Пользователь %s успешно добавлен.%n", newUser);
                            }
                        } else {
                            System.out.printf("Пользователь %s уже существует.%n", newUser);
                        }
                    } else {
                        System.err.println("Пользователь не добавлен.");
                    }
                }
                case "3" -> {
                    System.out.println(REMOVE_SUBMENU_TEXT);
                    switch (scanner.next()) {
                        case "1" -> {
                            usersService.printAllUsers();
                            System.out.println("Выберите номер пользователя из списка для удаления: ");
                            List<User> newUsersList = usersService.getListWithRemovedUser(Integer.parseInt(scanner.next()) - 1);
                            if (usersRepository.rewriteFile(newUsersList)) {
                                System.out.println("Пользователь успешно удален из списка.");
                            } else {
                                System.err.println("Ошибка удаления пользователя.");
                            }

                        }
                        case "2" -> {

                        }
                        case "0" -> {
                            System.out.println();
                        }
                        default -> System.out.println("Команда не распознана.");
                    }
                }
                case "4" -> {
                    System.out.print("Фамилия самого взрослого пользователя: ");
                    String lastName = usersService.getLastNameOfMostAging();
                    System.out.println(lastName);
                    System.out.println();
                }
                case "5" -> {
                    System.out.print("Средний возраст всех пользователей: ");
                    double averageAge = usersService.getAverageUsersAge();
                    System.out.printf("%.1f.%n", averageAge);
                    System.out.println();
                }
                case "6" -> {
                    System.out.print("Возраст самого высокого пользователя: ");
                    int ageOfHighest = usersService.getAgeOfHighest();
                    System.out.println(ageOfHighest);
                    System.out.println();
                }
                case "7" -> {
                    System.out.print("Имя и фамилия самого низкого пользователя: ");
                    String fullNameOfMinHeight = usersService.getFullNameOfMinHeight();
                    System.out.println(fullNameOfMinHeight + ".");
                    System.out.println();
                }
                case "0" -> {
                    System.out.println("Выход...");
                    System.exit(0);
                }
                default -> System.out.println("Команда не распознана.");
            }
        }
    }
}

