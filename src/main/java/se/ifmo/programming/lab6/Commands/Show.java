package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.storage.Storage;
import se.ifmo.programming.lab6.utils.Interpreter;

/**
 * Команда show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class Show extends Command{
    public static String description = "вывести в стандартный поток вывода все элементы коллекции " +
            "в строковом представлении";
    public static String syntax = "show";
    public CommandType commandType = CommandType.SHOW;

    public Show(){

    }

    public Show(Storage storage) {
        super(storage);
    }



    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){
        return storage.show();
    }

}
