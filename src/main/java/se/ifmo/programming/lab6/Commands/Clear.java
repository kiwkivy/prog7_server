package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда clear : очистить коллекцию.
 */

public class Clear extends Command {
    public static String description = "очистить коллекцию";
    public static String syntax = "clear";
    public CommandType commandType = CommandType.CLEAR;

    public Clear(Storage storage) {
        super(storage);
    }

    public Clear() {
    }
    public CommandType getCommandList() {
        return commandType;
    }

    @Override
    public String execute(){
        storage.clear();
        System.out.println("Коллекция очищена");
        return null;
    }

}
