package se.ifmo.programming.lab6.Commands;


import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.data.Dragon;
import se.ifmo.programming.lab6.exceptions.ElementNotValidException;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда add name age : добавить новый элемент в коллекцию.
 */
public class Add extends Command {
    public static transient String description = "добавить новый элемент в коллекцию";
    public static transient String syntax = "add {element}";
    private CommandType commandType = CommandType.ADD;
    private Dragon dragon;

    public CommandType getCommandList() {
        return commandType;
    }

    public Add(Storage storage, Dragon dragon) {
        super(storage,dragon);
    }

    public Add() {
    }

    public String execute(){
        Server.dragonVectorStorage.add(dragon);
        return "Элемент добавлен в коллекцию";

    }
}

