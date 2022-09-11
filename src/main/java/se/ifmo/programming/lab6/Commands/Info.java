package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда info : вывести в стандартный поток вывода информацию о коллекции
 * (тип, дата инициализации, количество элементов и т.д.).
 */

public class Info extends Command{
    public static String description = "вывести в стандартный поток вывода информацию о коллекции " +
            "(тип, дата инициализации, количество элементов и т.д.)";
    public static String syntax = "info";
    public CommandType commandType = CommandType.INFO;

    public Info(Storage storage) {
        super(storage);
    }

    public Info() {
    }

    public CommandType getCommandList() {return commandType;}

    public String execute(){
        System.out.println("Информация о коллекции:");
        storage.info();
        return null;
    }
}
