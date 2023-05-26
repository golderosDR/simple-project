package de.ait.repositories;

import de.ait.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryTextFileImpl implements UsersRepository {

    private String fileName;

    public UsersRepositoryTextFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line = bufferedReader.readLine();
            while (line != null) {
                User user = parseLine(line);
                users.add(user);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Неверно указано имя файла!");
        }
        return users;
    }

    @Override
    public boolean addToFile(User user) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.newLine();
            bufferedWriter.write(user.toFormattedString());

        } catch (IOException e) {
            System.err.println("Неверно указано имя файла!");
            return false;
        }
        return true;
    }

    @Override
    public boolean rewriteFile(List<User> users) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (User user: users) {
                bufferedWriter.write(user.toFormattedString());
                bufferedWriter.newLine();

            }
        } catch (IOException e) {
            System.err.println("Неверно указано имя файла!");
            return false;
        }
        return true;
    }

    private static User parseLine(String line) {
        //TODO обработать исключение, спросить у преподавателя
        try {
            String[] parsed = line.split("\\|");
            String firstName = parsed[0];
            String lastName = parsed[1];
            int age = Integer.parseInt(parsed[2]);
            double height = Double.parseDouble(parsed[3]);

            return new User(
                    firstName, lastName, age, height
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Неверный формат данных в файле!");
        }
        return null;
    }
}
