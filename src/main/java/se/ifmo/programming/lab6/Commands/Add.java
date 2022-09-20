package se.ifmo.programming.lab6.Commands;


import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.data.Dragon;
import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда add name age : добавить новый элемент в коллекцию.
 */
public class Add<E> extends Command {
    private String description = "добавить новый элемент в коллекцию";
    Dragon dragon = (Dragon) element;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    private String syntax = "add {element}";
    private CommandType commandType = CommandType.ADD;

    public CommandType getCommandList() {
        return commandType;
    }

    public Add(Storage storage, E element) {
        super(storage,element);
    }

    public Add() {
    }

    public String execute(){
        Server.dragonVectorStorage.add(dragon);
        return "Элемент добавлен в коллекцию";

    }
}

