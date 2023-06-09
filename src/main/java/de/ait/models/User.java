package de.ait.models;

import de.ait.exceptions.IllegalUserFieldArgumentException;

import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private double height;
    public static int USER_NAME_MIN_LENGTH_VALUE = 1;
    public static int USER_AGE_MIN_VALUE = 0;
    public static int USER_HEIGHT_MIN_VALUE = 0;

    public User(String firstName, String lastName, int age, double height) {
        if (firstName.length() < USER_NAME_MIN_LENGTH_VALUE|| lastName.length() < USER_NAME_MIN_LENGTH_VALUE) {
            throw new IllegalUserFieldArgumentException(IllegalUserFieldArgumentException.WRONG_NAME_SIZE_MESSAGE);
        }
        if (age < USER_AGE_MIN_VALUE ) {
            throw new IllegalUserFieldArgumentException(IllegalUserFieldArgumentException.WRONG_AGE_VALUE_MESSAGE);
        }
        if (height < USER_HEIGHT_MIN_VALUE) {
            throw new IllegalUserFieldArgumentException(IllegalUserFieldArgumentException.WRONG_HEIGHT_VALUE_MESSAGE);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("%s %s, age %d, height %.2f",
                firstName,
                lastName,
                age,
                height
        );
    }
    public String toFormattedString() {
        return String.format("%s|%s|%d|%.2f",
                firstName,
                lastName,
                age,
                height
        ).replaceFirst(",", ".");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return age == user.age
                && Double.compare(user.height, height) == 0
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, height);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
