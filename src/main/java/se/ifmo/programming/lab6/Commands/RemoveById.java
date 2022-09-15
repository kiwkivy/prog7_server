package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда remove_by_id id : удалить элемент из коллекции по его id.
 */

public class RemoveById extends Command {
    int id;
    public static String description = "удалить элемент из коллекции по его id";
    public static String syntax = "remove_by_id id";
    public CommandType commandType = CommandType.REMOVE_BY_ID;


    public RemoveById(Storage storage, int id) {
        super(storage);
        this.id = id;
    }

    public RemoveById() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        storage.removeById(id);
        return ("Элемент с id "+id+" удалён из коллекции");
    }
}
