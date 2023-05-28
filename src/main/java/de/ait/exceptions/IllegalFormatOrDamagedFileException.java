package de.ait.exceptions;

public class IllegalFormatOrDamagedFileException extends RuntimeException{
    public static String ILLEGAL_FORMAT_OR_DAMAGED_FILE = "Файл содержит элементы неподдерживаемого формата или поврежден.";
    public IllegalFormatOrDamagedFileException(String message) {
        super(message);
    }

}
