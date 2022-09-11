package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный.
 */

public class RemoveLower<E> extends Command{
    public static String description = "удалить из коллекции все элементы, меньшие, чем заданный";
    public static String syntax = "remove_lower id";
    public CommandType commandType = CommandType.REMOVE_LOWER;

    public RemoveLower(Storage storage, E element) {
        super(storage);
        this.element = element;
    }

    public RemoveLower() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        storage.removeLower(element);
        System.out.println("Элементы, меньшее заданного, удалены из коллекции");
        return null;
    }
}
