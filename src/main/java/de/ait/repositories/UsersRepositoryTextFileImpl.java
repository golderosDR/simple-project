package de.ait.repositories;

import de.ait.exceptions.IllegalFormatOrDamagedFileException;
import de.ait.messages.ErrorMessages;
import de.ait.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static de.ait.exceptions.IllegalFormatOrDamagedFileException.ILLEGAL_FORMAT_OR_DAMAGED_FILE;

public class UsersRepositoryTextFileImpl implements UsersRepository {

    private final String fileName;

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
                if (line.equals("")) {
                    throw  new IllegalFormatOrDamagedFileException(ILLEGAL_FORMAT_OR_DAMAGED_FILE);
                }
                User user = parseLine(line);
                users.add(user);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println(ErrorMessages.FILE_NOT_EXISTS_ERROR);
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
            System.err.println(ErrorMessages.FILE_NOT_EXISTS_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public boolean rewriteFile(List<User> users) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (int i = 0; i < users.size(); i++) {
                bufferedWriter.write(users.get(i).toFormattedString());
                if (i < users.size() - 1) {
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println(ErrorMessages.FILE_NOT_EXISTS_ERROR);
            return false;
        }
        return true;
    }

    private static User parseLine(String line) {
        try {
            String[] parsed = line.split("\\|");
            String firstName = parsed[0];
            String lastName = parsed[1];
            int age = Integer.parseInt(parsed[2]);
            double height = Double.parseDouble(parsed[3]);

            return new User(firstName, lastName, age, height);

        } catch (RuntimeException e) {
            throw new IllegalFormatOrDamagedFileException(ILLEGAL_FORMAT_OR_DAMAGED_FILE);
        }
    }
}
