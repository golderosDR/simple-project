package de.ait.repositories;

import de.ait.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryListImpl implements UsersRepository {
    @Override
    public List<User> findAll() {
        User user1 = new User("fName1", "lName1", 20, 1.81);
        User user2 = new User("fName2", "lName2", 35, 1.85);
        User user3 = new User("fName3", "lName3", 30, 1.90);
        User user4 = new User("fName4", "lName4", 50, 1.60);
        User user5 = new User("fName5", "lName5", 32, 1.99);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        return users;
    }

    @Override
    public boolean rewriteFile(List<User> users) {
        return false;
    }

    @Override
    public boolean addToFile(User user) {
        return false;
    }
}
