package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.storage.Storage;

/**
 * Команда reorder : отсортировать коллекцию в порядке, обратном нынешнему.
 */

public class Reorder extends Command {
    private String description = "отсортировать коллекцию в порядке, обратном нынешнему";
    private String syntax = "reorder";
    private CommandType commandType = CommandType.REORDER;

    public Reorder(Storage storage) {
        super(storage);
    }

    public Reorder() {
    }

    public CommandType getCommandType() {return commandType;}

    @Override
    public String execute(){
        storage.reorder();
        return "Коллекция отсортирована в обратном порядке";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }
}
