package de.ait.models;

import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private double height;

    public User(String firstName, String lastName, int age, double height) {
        if (firstName.length() < 1 || lastName.length() < 1) {
            throw new IllegalUserFieldArgumentException(IllegalUserFieldArgumentException.WRONG_NAME_SIZE_MESSAGE);
        }
        if (age < 0) {
            throw new IllegalUserFieldArgumentException(IllegalUserFieldArgumentException.WRONG_AGE_VALUE_MESSAGE);
        }
        if (height < 0) {
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
}
