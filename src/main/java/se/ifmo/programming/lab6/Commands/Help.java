package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;

/**
 * Комманда help : вывести справку по доступным командам.
 */

public class Help extends Command{
    private transient String description = "вывести справку по доступным командам";
    private transient String syntax = "help";
    public CommandType commandType = CommandType.HELP;

    public Help() {
    }
    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        return Server.listOfCommands.stream().map(s -> "- " + s.getSyntax() + ": " + s.getDescription() + "\n").reduce((s1, s2) -> s1 + s2).get();
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
