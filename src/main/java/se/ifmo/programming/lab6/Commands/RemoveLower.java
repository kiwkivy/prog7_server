package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.data.Dragon;
import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный.
 */

public class RemoveLower<E> extends Command{
    public static String description = "удалить из коллекции все элементы, меньшие, чем заданный";
    public static String syntax = "remove_lower id";
    public CommandType commandType = CommandType.REMOVE_LOWER;
    private int id;

    public RemoveLower(Storage storage, Dragon dragon) {
        super(storage, dragon);
    }

    public RemoveLower() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        storage.removeLower(id);
        return "Элементы, меньшее заданного, удалены из коллекции";
    }
}
