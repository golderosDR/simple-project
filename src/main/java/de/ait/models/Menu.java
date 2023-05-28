package de.ait.models;


public abstract class Menu {
    private final static String MAIN_MENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести всех пользователей.
            2. Сохранить нового пользователя.
            3. Изменить данные пользователя.
            4. Удалить пользователя.
            5. Вспомогательные функции.
            0. Выход.""";
    private final static String CHANGE_DATA_SUBMENU_TEXT = """
           Алгоритм изменения данных пользователя:
           1. Изменить все данные пользователя.
           2. Изменить отдельный параметр.
           0. Отмена.""";
    private final static String CHOOSE_UR_DESTINY_SUBMENU_TEXT = """
           Какие данные пользователя вы бы хотели изменить?
           1. Имя.
           2. Фамилию.
           3. Возраст.
           4. Рост.
           0. Отмена.""";
    private final static String REMOVE_SUBMENU_TEXT = """
           Алгоритм удаления пользователя:
           1. Удалить пользователя из списка пользователей по номеру.
           2. Удалить пользователя по введенным данным.
           0. Отмена.""";
    private final static String UTILITY_SUBMENU_TEXT = """
            Введите необходимый пункт меню для выполнения:
            1. Вывести имена всех пользователей.
            2. Вывести фамилию самого взрослого пользователя.
            3. Вывести средний возраст всех пользователей.
            4. Вывести возраст самого высокого пользователя.
            5. Вывести имя и фамилию самого низкого пользователя.
            0. Назад в главное меню.""";
public final static String CANCEL_OPERATION = "Операция отменена.";
    public final static String WRONG_COMMAND = "Команда не распознана.";

    public static void showMainMenu() {
        System.out.println(MAIN_MENU_TEXT);
    }
    public static void showChangeDataSubmenu() {System.out.println(CHANGE_DATA_SUBMENU_TEXT);}
    public static void showChoseParameterSubmenu() {System.out.println(CHOOSE_UR_DESTINY_SUBMENU_TEXT);}
    public static void showRemoveSubmenu() {
        System.out.println(REMOVE_SUBMENU_TEXT);
    }
    public static void showUtilitySubmenu() {
        System.out.println(UTILITY_SUBMENU_TEXT);
    }
    public static void menuCancel() {System.out.println(CANCEL_OPERATION); }
    public static void wrongCommand() { System.out.println(WRONG_COMMAND); }

}
