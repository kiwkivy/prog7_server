package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;

/**
 * Команда print_field_descending_cave : вывести значения поля cave всех элементов в порядке убывания.
 */

public class PrintFieldDescendingCave extends Command {
    private String description = "вывести значения поля cave всех элементов в порядке убывания";
    private String syntax = "print_field_descending_cave";
    private CommandType commandType = CommandType.PRINT_FIELD_DESCENDING_CAVE;


    public PrintFieldDescendingCave() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){

        return Server.dragonVectorStorage.getAllDescendingCave();
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
