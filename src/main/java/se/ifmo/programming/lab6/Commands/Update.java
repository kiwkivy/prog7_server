package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.exceptions.ElementNotValidException;
import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда update id name age : обновить значение элемента коллекции, id которого равен заданному.
 */

public class Update<E> extends Command {
    private int id;
    public static String description = "обновить значение элемента коллекции, id которого равен заданному";
    public static String syntax = "update id {element}";
    public CommandType commandType = CommandType.UPDATE;

    public Update() {
    }

    public Update(Storage storage, E element, int id) {
        super(storage);
        this.element = element;
        this.id = id;
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        try {
            storage.update(id, element);
            System.out.println("Элемент с id "+ id++ +" обновлён");
        }catch (ElementNotValidException e){
        }
        return null;
    }
}
