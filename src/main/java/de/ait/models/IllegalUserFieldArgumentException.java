package de.ait.models;

public class IllegalUserFieldArgumentException extends RuntimeException {
    public static String WRONG_NAME_SIZE_MESSAGE = "Недопустимое значение длинны имени или фамилии";
    public static String WRONG_AGE_VALUE_MESSAGE = "Недопустимое значение возраста пользователя";
    public static String WRONG_HEIGHT_VALUE_MESSAGE = "Недопустимое значение роста пользователя";
    IllegalUserFieldArgumentException(String message) {
        super(message);
    }

}
