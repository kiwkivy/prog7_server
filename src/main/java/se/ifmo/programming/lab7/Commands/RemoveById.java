package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.storage.Storage;

/**
 * Команда remove_by_id id : удалить элемент из коллекции по его id.
 */

public class RemoveById extends Command {
    int id;
    private String description = "удалить элемент из коллекции по его id";
    private String syntax = "remove_by_id id";
    private CommandType commandType = CommandType.REMOVE_BY_ID;


    public RemoveById(Storage storage, int id) {
        super(storage);
        this.id = id;
    }

    public RemoveById() {
    }

    public CommandType getCommandType() {return commandType;}

    @Override
    public String execute() {
        if(Server.databaseWorker.removeDragonByID(id, username))
            return ("Элемент с id "+id+" удалён из коллекции");
        else return "У вас нет такого дракона";
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
