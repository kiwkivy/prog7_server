package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.Storage;

/**
 * Команда reorder : отсортировать коллекцию в порядке, обратном нынешнему.
 */

public class Reorder extends Command {
    public static String description = "отсортировать коллекцию в порядке, обратном нынешнему";
    public static String syntax = "reorder";
    public CommandType commandType = CommandType.REORDER;

    public Reorder(Storage storage) {
        super(storage);
    }

    public Reorder() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){
        System.out.println("Коллекция отсортирована в обратном порядке");
        storage.reorder();
        return null;
    }
}
