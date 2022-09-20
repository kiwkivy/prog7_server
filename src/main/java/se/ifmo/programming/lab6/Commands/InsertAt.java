package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.data.Dragon;
import se.ifmo.programming.lab6.exceptions.ElementNotValidException;
import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда insert_at index name age : добавить новый элемент в заданную позицию.
 */

public class InsertAt<E> extends Command {
    private int index;
    private String description = "добавить новый элемент в заданную позицию";
    private String syntax = "insert_at index {element}";
    private CommandType commandType = CommandType.INSERT_AT;
    private Dragon dragon = (Dragon) element;

    public InsertAt(Storage storage, E element, int index) {
        super(storage, element);
        this.index = index;
    }

    public InsertAt() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){
        try {
            index-=1;
            storage.insertAt(index, dragon);
            return ("Эленент добавлин в позицию "+dragon.getId());
        }catch (ElementNotValidException e){
        }
        return null;
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
