package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки.
 */

public class FilterStartsWithName extends Command {
    private String name;
    public static String description = "вывести элементы, значение поля name которых начинается с заданной подстроки";
    public static String syntax = "filter_starts_with_name name";
    public CommandType commandType = CommandType.FILTER_STARTS_WITH_NAME;

    public FilterStartsWithName(Storage storage, String name) {
        super(storage);
        this.name = name;
    }

    public FilterStartsWithName() {
    }
    public CommandType getCommandList() {
        return commandType;
    }

    @Override
    public String execute() {
        System.out.println("Элементы коллекции, начинающиеся с <" + name + ">:");
        storage.filterStartsWithName(name);
        return null;
    }
}
