package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.data.Dragon;
import se.ifmo.programming.lab7.storage.Storage;


/**
 * Команда remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный.
 */

public class RemoveLower<E> extends Command{
    private String description = "удалить из коллекции все элементы, меньшие, чем заданный";
    private String syntax = "remove_lower dragon";
    private CommandType commandType = CommandType.REMOVE_LOWER;
    private Dragon dragon = (Dragon) element;

    public RemoveLower(Storage storage, E element) {
        super(storage, element);
    }

    public RemoveLower() {
    }

    public CommandType getCommandType() {return commandType;}

    @Override
    public String execute() {
        Server.databaseWorker.removeLower(username, dragon.getAge());
        return "Ваши элементы, меньшее заданного, удалены из коллекции";
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
