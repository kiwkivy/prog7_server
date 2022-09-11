package se.ifmo.programming.lab6.Commands;


import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.exceptions.ElementNotValidException;
import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда add name age : добавить новый элемент в коллекцию.
 */
public class Add<E> extends Command {
    public static String description = "добавить новый элемент в коллекцию";
    public static String syntax = "add {element}";
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
        //Server.dragonVectorStorage.add(element);
        return "Элемент добавлен в коллекцию";

    }
}

