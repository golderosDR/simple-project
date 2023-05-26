package de.ait.services;

import de.ait.models.User;
import de.ait.repositories.UsersRepositoryListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceImplTest {
    private UsersServiceImpl usersService;
    private UsersRepositoryListImpl usersRepositoryList;

    @BeforeEach
    void setUp() {
        this.usersRepositoryList = new UsersRepositoryListImpl();
        this.usersService = new UsersServiceImpl(this.usersRepositoryList);
    }

    @Test
    void getNames() {
        List<String> actual = usersService.getNames();
        List<String> expected = new ArrayList<>(List.of("fName1", "fName2", "fName3", "fName4", "fName5"));
        assertEquals(expected, actual);

    }


    @Test
    void containsInRepository_true() {
        List<User> userList = usersRepositoryList.findAll();
        boolean actual = usersService.containsInRepository(new User("fName3", "lName3", 30, 1.90));
        assertTrue(actual);
    }
    @Test
    void containsInRepository_false() {
        List<User> userList = usersRepositoryList.findAll();
        boolean actual = usersService.containsInRepository( new User("fName3", "lName3", 42, 1.91));
        assertFalse(actual);
    }
/*
    @Test
    void getFullNameOfMinHeight() {
        String actual;
        String expected;
        boolean
    }*/
}
