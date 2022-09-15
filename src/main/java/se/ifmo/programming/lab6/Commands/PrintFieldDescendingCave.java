package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.utils.Interpreter;

import java.util.Collection;

/**
 * Команда print_field_descending_cave : вывести значения поля cave всех элементов в порядке убывания.
 */

public class PrintFieldDescendingCave extends Command {
    public static String description = "вывести значения поля cave всех элементов в порядке убывания";
    public static String syntax = "print_field_descending_cave";
    public CommandType commandType = CommandType.PRINT_FIELD_DESCENDING_CAVE;


    public PrintFieldDescendingCave() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){

        return Server.dragonVectorStorage.getAllDescendingCave();
    }
}
