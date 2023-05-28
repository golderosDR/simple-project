package de.ait.app;

import de.ait.models.Menu;
import de.ait.repositories.UsersRepository;
import de.ait.repositories.UsersRepositoryListImpl;
import de.ait.repositories.UsersRepositoryTextFileImpl;
import de.ait.services.UsersService;
import de.ait.services.UsersServiceImpl;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersRepository usersRepository = new UsersRepositoryTextFileImpl("users.txt");
        UsersRepository testUserRepository = new UsersRepositoryListImpl();
        UsersService usersService = new UsersServiceImpl(usersRepository);

        while (true) {
            Menu.showMainMenu();
            String command = scanner.next();

            switch (command) {
                case "1" -> usersService.printAllUsers();

                case "2" -> usersService.saveNewUser();

                case "3" -> usersService.changeUserData();

                case "4" -> {
                    Menu.showRemoveSubmenu();
                    switch (scanner.next()) {
                        case "1" -> usersService.removeUserByNumber();

                        case "2" -> usersService.removeUserByInput();

                        case "0" -> Menu.menuCancel();
                        default -> Menu.wrongCommand();
                    }
                }
                case "5" -> {
                    Menu.showUtilitySubmenu();
                    String temp = scanner.next();
                    while (!temp.equals("0")) {
                        switch (temp) {
                            case "1" -> usersService.printAllNames();

                            case "2" -> System.out.printf("Фамилия самого взрослого пользователя: %s.%n%n",
                                    usersService.getLastNameOfMostAging());

                            case "3" -> System.out.printf("Средний возраст всех пользователей: %.1f.%n%n",
                                    usersService.getAverageUsersAge());

                            case "4" -> System.out.printf("Возраст самого высокого пользователя: %d.%n%n",
                                    usersService.getAgeOfHighest());

                            case "5" -> System.out.printf("Имя и фамилия самого низкого пользователя: %s.%n%n",
                                        usersService.getFullNameOfMinHeight());

                            default -> Menu.wrongCommand();
                        }
                        Menu.showUtilitySubmenu();
                        temp = scanner.next();
                    }
                }
                case "0" -> {
                    System.out.println("Выход...");
                    scanner.close();
                    System.exit(0);
                }
                default -> Menu.wrongCommand();
            }
        }
    }
}

