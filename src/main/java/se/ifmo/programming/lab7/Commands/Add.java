package se.ifmo.programming.lab7.Commands;


import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.data.Dragon;
import se.ifmo.programming.lab7.storage.Storage;

/**
 * Команда add name age : добавить новый элемент в коллекцию.
 */
public class Add<E> extends Command {
    private String description = "добавить новый элемент в коллекцию";
    Dragon dragon = (Dragon) element;

    private String syntax = "add {element}";
    private CommandType commandType = CommandType.ADD;

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    public Add(Storage storage, E element) {
        super(storage,element);
    }

    public Add() {
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    public String execute(){
        Server.dragonVectorStorage.add(dragon);
        Server.databaseWorker.insertDragon(dragon, username);
        Server.databaseWorker.loadCollectionFromDB();
        return "Элемент добавлен в коллекцию";

    }
}

