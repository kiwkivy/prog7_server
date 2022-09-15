package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.data.Dragon;
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
    private Dragon dragon;

    public Update() {
    }

    public Update(Storage storage, Dragon dragon, int id) {
        super(storage, dragon);
        this.id = id;
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        try {
            id-=1;
            storage.update(id, dragon);
            id+=2;
            return "Элемент с id "+ id +" обновлён";
        }catch (ElementNotValidException e){
        }
        return null;
    }
}
