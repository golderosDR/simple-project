package de.ait.repositories;

import de.ait.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryListImpl implements UsersRepository {
    private List<User> users = new ArrayList<>(List.of(
            new User("fName1", "lName1", 20, 1.81),
            new User("fName2", "lName2", 35, 1.85),
            new User("fName3", "lName3", 30, 1.90),
            new User("fName4", "lName4", 50, 1.60),
            new User("fName5", "lName5", 32, 1.99)
    ));
    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean rewriteFile(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            if (!this.users.get(i).equals(users.get(i)))   {
                return false;
            }
        }
        this.users = users;
        return true;
    }

    @Override
    public boolean addToFile(User user) {
        return users.add(user);
    }
}

