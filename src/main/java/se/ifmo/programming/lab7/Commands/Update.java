package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.data.Dragon;
import se.ifmo.programming.lab7.storage.Storage;

/**
 * Команда update id name age : обновить значение элемента коллекции, id которого равен заданному.
 */

public class Update<E> extends Command {
    private int id;
    private String description = "обновить значение элемента коллекции, id которого равен заданному";
    private String syntax = "update id {element}";
    private CommandType commandType = CommandType.UPDATE;
    private Dragon dragon;

    public Update() {
    }

    public Update(Storage storage, Dragon dragon, int id) {
        super(storage, dragon);
        this.id = id;
    }

    public CommandType getCommandType() {return commandType;}

    @Override
    public String execute() {
        Server.databaseWorker.updateDragon(dragon, id);
        Server.databaseWorker.loadCollectionFromDB();
        return "Элемент с id "+ id +" обновлён";
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
