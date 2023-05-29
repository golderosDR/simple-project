package de.ait.services;

import de.ait.models.User;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class TestParameters {

    static Stream<Arguments> stringToUser_Success() {
        return Stream.of(
                Arguments.of(new InputtedStringToUser("fName1 lName1 20 1.81", new User("fName1", "lName1", 20, 1.81))),
                Arguments.of(new InputtedStringToUser("fName2 lName2 35 1.85", new User("fName2", "lName2", 35, 1.85))),
                Arguments.of(new InputtedStringToUser("fName3 lName3 30 1.90", new User("fName3", "lName3", 30, 1.90)))
        );
    }
    static Stream<Arguments> stringToUser_Fail() {
        return Stream.of(
                Arguments.of(new InputtedStringToUser("fNa1me1 lName1 20 1.81", new User("fName1", "lName1", 20, 1.81))),
                Arguments.of(new InputtedStringToUser("fName2 lNa1me2 35 1.85", new User("fName2", "lName2", 35, 1.85))),
                Arguments.of(new InputtedStringToUser("fName2 lName2 35 1.5", new User("fName2", "lName2", 35, 1.85))),
                Arguments.of(new InputtedStringToUser("fName3 lName3 32 1.90", new User("fName3", "lName3", 30, 1.90)))
        );
    }
    static Stream<Arguments> InputtedStringToFoundUserList_Success() {
        return Stream.of(
                Arguments.of(new InputtedStringToFoundUserList(
                        "fName1" ,
                        List.of(new User("fName1", "lName1", 20, 1.81))
                )),
                Arguments.of(new InputtedStringToFoundUserList(
                        "lName2" ,
                        List.of(new User("fName2", "lName2", 35, 1.85))
                )),
                Arguments.of(new InputtedStringToFoundUserList(
                        "fName4 lName4" ,
                        List.of(new User("fName4", "lName4", 50, 1.60))
                )),
                Arguments.of(new InputtedStringToFoundUserList(
                        "fName7" ,
                        new ArrayList<>()
                ))
        );
    }
    static Stream<Arguments> InputtedStringToFoundUserList_Fail() {
        return Stream.of(
                Arguments.of(new InputtedStringToFoundUserList(
                        "fName" ,
                        List.of(new User("fName1", "lName1", 20, 1.81))
                )),
                Arguments.of(new InputtedStringToFoundUserList(
                        " " ,
                        List.of(new User("fName2", "lName2", 35, 1.85))
                )),
                Arguments.of(new InputtedStringToFoundUserList(
                        "fName4, lName4" ,
                        List.of(new User("fName4", "lName4", 50, 1.60))
                )),
                Arguments.of(new InputtedStringToFoundUserList(
                        "fName4 lName4 11123" ,
                        List.of(new User("fName4", "lName4", 50, 1.60))
                ))
        );
    }

}
class InputtedStringToUser {
    public String input;
    public User userExpected;
    InputtedStringToUser(String input, User userExpected) {
        this.input = input;
        this.userExpected = userExpected;
    }
    @Override
    public String toString() {
        return "input ='" + input + ", userExp =" + userExpected;
    }
}

class InputtedStringToFoundUserList {
    public String input;
    public List<User> userListExpected;
    InputtedStringToFoundUserList(String input, List<User> userListExpected) {
        this.input = input;
        this.userListExpected = userListExpected;
    }
}