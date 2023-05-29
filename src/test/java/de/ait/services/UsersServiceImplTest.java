package de.ait.services;

import de.ait.models.User;
import de.ait.repositories.UsersRepositoryListImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.List.*;
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
        List<String> expected = new ArrayList<>(of("fName1", "fName2", "fName3", "fName4", "fName5"));
        assertEquals(expected, actual);

    }
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForGetUsersByInputtedName {
        @ParameterizedTest
        @MethodSource("de.ait.services.TestParameters#InputtedStringToFoundUserList_Success")
        void getUsersByInputtedName_Success(InputtedStringToFoundUserList inputtedStringToFoundUserList) {
            ByteArrayInputStream in = new ByteArrayInputStream(inputtedStringToFoundUserList.input.getBytes());
            System.setIn(in);
            System.out.println();
            assertEquals(inputtedStringToFoundUserList.userListExpected, usersService.getUsersByInputtedName());
        }
        @ParameterizedTest
        @MethodSource("de.ait.services.TestParameters#InputtedStringToFoundUserList_Fail")
        void getUsersByInputtedName_Fail(InputtedStringToFoundUserList inputtedStringToFoundUserList) {
            ByteArrayInputStream in = new ByteArrayInputStream(inputtedStringToFoundUserList.input.getBytes());
            System.setIn(in);
            System.out.println();
            assertNotEquals(inputtedStringToFoundUserList.userListExpected, usersService.getUsersByInputtedName());
        }
    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForChangeAllUsersDataTest {
        @ParameterizedTest
        @CsvSource({
                "1, 'fName2 lName1 20 1.81'",
                "1, 'fName1 lName2 20 1.81'",
                "1, 'fName1 lName1 22 1.81'",
                "1, 'fName1 lName1 20 1.82'",
        })
        void changeAllUsersData_Success(int number, String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertTrue(usersService.changeAllUsersData(number));
        }
        @ParameterizedTest
        @CsvSource({
                "1, 'fName1 lName1 20 1.81'",
                "1, 'fName1 lName2 20'",
                "1, ' '",
                "1, 'fName1 lName1 20 1,82'",
                "1, 'fName1 lName1 2,0 1.82'",
        })
        void changeAllUsersData_Fail_With_Message(int number, String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertFalse(usersService.changeAllUsersData(number));
        }
    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForRemoveUserByInputTest {
        @ParameterizedTest
        @ValueSource(strings = {
                "fName1 lName1 20 1.81",
                "fName2 lName2 35 1.85",
                "fName3 lName3 30 1.90",
        })
        void removeUserByInput_Success(String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertTrue(usersService.removeUserByInput());
        }
        @ParameterizedTest
        @ValueSource(strings = {
                "fName6 lName6 35 1.99",
                "11 11 11",
                " ",
                "11 11",
                "11",
                "11 11 11 1,1",
        })
        void removeUserByInput_Fail_With_Message(String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertFalse(usersService.removeUserByInput());
        }
    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForRemoveUserByNumberTest {
        @ParameterizedTest
        @ValueSource(strings = {"1", "2", "3", "4", "5"})
        void removeUserByNumber_Success(String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertTrue(usersService.removeUserByNumber());
        }
        @ParameterizedTest
        @ValueSource(strings = {"7", "1.2", "12", "adf", "1,2"})
        void removeUserByNumber_Fail_With_Message(String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertFalse(usersService.removeUserByNumber());
        }

    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForSaveNewUserTest {
        @ParameterizedTest
        @ValueSource(strings = {
                "fName1 lName1 20 1.80",
                "fName1 lName1 30 1.81",
                "fName7 lName1 20 1.81",
                "fName1 lName7 20 1.81",
                "fName6 lName6 35 1.99"
        })
        void saveNewUser_Success(String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertTrue(usersService.saveNewUser());
        }
        @ParameterizedTest
        @ValueSource(strings = {
                "fName5 lName5 32 1.99",
                "11 11 11",
                " ",
                "11 11",
                "11",
                "11 11 11 1,1"})
        void saveNewUser_Failed_With_Message(String input) {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            System.out.println();
            assertFalse(usersService.saveNewUser());
        }

    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForGetUserFromScannerTest {
        @ParameterizedTest
        @MethodSource("de.ait.services.TestParameters#stringToUser_Success")
        public void getUserFromScanner_Success(InputtedStringToUser inputtedStringToUser) {
            ByteArrayInputStream in = new ByteArrayInputStream(inputtedStringToUser.input.getBytes());
            System.setIn(in);
            User actual = usersService.getUserFromScanner();
            assertEquals(inputtedStringToUser.userExpected, actual);
        }

        @ParameterizedTest
        @MethodSource("de.ait.services.TestParameters#stringToUser_Fail")
        public void getUserFromScanner_Fail(InputtedStringToUser inputtedStringToUser) {
            ByteArrayInputStream in = new ByteArrayInputStream(inputtedStringToUser.input.getBytes());
            System.setIn(in);
            User actual = usersService.getUserFromScanner();
            assertNotEquals(inputtedStringToUser.userExpected, actual);
        }

        @ParameterizedTest
        @ValueSource(strings = {"deaf", "11 11 11", "", "11 11 11 1,1"})
        public void getUserFromScanner_Null_Returned_Error_Printed(String input) {
            assertNull(usersService.parseLine(input));

        }
    }


    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForContainsInRepositoryTest {
        @Test
        void containsInRepository_true() {
            boolean actual = usersService.containsInRepository(new User("fName3", "lName3", 30, 1.90));
            assertTrue(actual);
        }

        @Test
        void containsInRepository_false() {
            boolean actual = usersService.containsInRepository(new User("fName3", "lName3", 42, 1.91));
            assertFalse(actual);
        }
    }

    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class ForGetAgeOfHighestTest {
        @Test
        void getAgeOfHighest_act_32_exp_32() {
            int actual = usersService.getAgeOfHighest();
            int expected = 32;
            assertEquals(expected, actual);
        }

        @Test
        void getAgeOfHighest_act_32_exp_50() {
            int actual = usersService.getAgeOfHighest();
            int expected = 50;
            assertNotEquals(expected, actual);
        }

    }

    /*
руслан - методы getAverageUsersAge(), getLastNameOfMostAging()

александр - getListWithRemovedUser(int i), getFullNameOfMinHeight()*/
}
