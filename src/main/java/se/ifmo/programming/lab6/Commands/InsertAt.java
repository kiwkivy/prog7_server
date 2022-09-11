package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.exceptions.ElementNotValidException;
import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда insert_at index name age : добавить новый элемент в заданную позицию.
 */

public class InsertAt<E> extends Command {
    private int index;
    public static String description = "добавить новый элемент в заданную позицию";
    public static String syntax = "insert_at index {element}";
    public CommandType commandType = CommandType.INSERT_AT;

    public InsertAt(Storage storage, E element, int index) {
        super(storage, element);
        this.index = index-1;
    }

    public InsertAt() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){
        try {
            storage.insertAt(index, element);
            System.out.println("Эленент добавлин в позицию "+index);
        }catch (ElementNotValidException e){
        }
        return null;
    }
}
