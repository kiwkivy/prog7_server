package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда info : вывести в стандартный поток вывода информацию о коллекции
 * (тип, дата инициализации, количество элементов и т.д.).
 */

public class Info extends Command{
    private String description = "вывести в стандартный поток вывода информацию о коллекции " +
            "(тип, дата инициализации, количество элементов и т.д.)";
    private String syntax = "info";
    private CommandType commandType = CommandType.INFO;

    public Info(Storage storage) {
        super(storage);
    }

    public Info() {
    }

    public CommandType getCommandList() {return commandType;}

    public String execute(){
        return storage.info();
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
