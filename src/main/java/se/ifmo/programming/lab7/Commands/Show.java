package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.storage.Storage;

/**
 * Команда show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class Show extends Command{
    private String description = "вывести в стандартный поток вывода все элементы коллекции " +
            "в строковом представлении";
    private String syntax = "show";
    private CommandType commandType = CommandType.SHOW;

    public Show(){

    }

    public Show(Storage storage) {
        super(storage);
    }



    public CommandType getCommandType() {return commandType;}

    @Override
    public String execute(){
        return storage.show();
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
