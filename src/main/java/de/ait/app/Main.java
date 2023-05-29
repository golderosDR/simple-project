package de.ait.app;

import de.ait.messages.ErrorMessages;
import de.ait.models.Menu;
import de.ait.repositories.UsersRepository;
import de.ait.repositories.UsersRepositoryTextFileImpl;
import de.ait.services.UsersService;
import de.ait.services.UsersServiceImpl;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersRepository usersRepository = new UsersRepositoryTextFileImpl("users.txt");
        UsersService usersService = new UsersServiceImpl(usersRepository);

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {
            }
            Menu.showMainMenu();


            switch (scanner.next()) {
                case "1" -> usersService.printAllUsers();

                case "2" -> usersService.getAndPrintUsersByInputtedName();

                case "3" -> {
                    if (!usersService.saveNewUser()) {
                        System.err.println(ErrorMessages.UNABLE_TO_ADD_USER_ERROR);
                    }
                }

                case "4" -> {
                    usersService.printAllUsers();
                        Menu.Command command = Menu.getCommand();
                    if (usersService.getUserListSize() < command.getInputtedCounter()) {
                        command.setCorrect(false);
                    }
                        if (command.isCorrect()) {

                            Menu.showChangeDataSubmenu();

                            switch (scanner.next()) {
                                case "1" -> {
                                    if (!usersService.changeAllUsersData(command.getInputtedCounter())) {
                                        System.err.println(ErrorMessages.UNABLE_TO_CHANGE_USERS_DATA_ERROR);
                                    }
                                }
                                case "2" -> {
                                    if (!usersService.changeRequiredUserData(command.getInputtedCounter()))
                                        System.err.println(ErrorMessages.UNABLE_TO_CHANGE_USERS_DATA_ERROR);
                                }
                                case "0" -> Menu.menuCancel();

                                default -> Menu.wrongCommand();
                            }
                        } else {
                            Menu.wrongCommand();
                        }
                    }


                case "5" -> {
                    Menu.showRemoveSubmenu();
                    switch (scanner.next()) {
                        case "1" -> {
                            if (!usersService.removeUserByNumber()) {
                                System.err.println(ErrorMessages.UNABLE_TO_REMOVE_USER_ERROR);
                            }
                        }
                        case "2" -> {
                            if (!usersService.removeUserByInput()) {
                                System.err.println(ErrorMessages.UNABLE_TO_REMOVE_USER_ERROR);
                            }
                        }
                        case "0" -> Menu.menuCancel();
                        default -> Menu.wrongCommand();
                    }
                }
                case "6" -> {
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

