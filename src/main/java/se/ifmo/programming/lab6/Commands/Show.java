package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class Show extends Command{
    public static String description = "вывести в стандартный поток вывода все элементы коллекции " +
            "в строковом представлении";
    public static String syntax = "show";
    public CommandType commandType = CommandType.SHOW;

    public Show(Storage storage) {
        super(storage);
    }

    public Show() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){
        System.out.println("Вывод коллекции:");
        storage.show();
        return null;
    }
}
