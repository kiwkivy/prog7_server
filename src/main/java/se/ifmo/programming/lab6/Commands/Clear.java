package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда clear : очистить коллекцию.
 */

public class Clear extends Command {
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    private String description = "очистить коллекцию";
    private String syntax = "clear";
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
        return "Коллекция очищена.";
    }

}
