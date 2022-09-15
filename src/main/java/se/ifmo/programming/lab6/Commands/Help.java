package se.ifmo.programming.lab6.Commands;

/**
 * Комманда help : вывести справку по доступным командам.
 */

public class Help extends Command{
    public static transient String description = "вывести справку по доступным командам";
    public static transient String syntax = "help";
    public CommandType commandType = CommandType.HELP;

    public Help() {
    }
    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        String result = "Вывод справки:";
        result+=("- " + Add.syntax + ": " + Add.description + "\n");
        result+=("- " + Clear.syntax + ": " + Clear.description + "\n");
        result+=("- " + CountByColor.syntax + ": " + CountByColor.description + "\n");
        result+=("- " + ExecuteScript.syntax + ": " + ExecuteScript.description + "\n");
        result+=("- " + FilterStartsWithName.syntax + ": " + FilterStartsWithName.description + "\n");
        result+=("- " + Help.syntax + ": " + Help.description + "\n");
        result+=("- " + Info.syntax + ": " + Info.description + "\n");
        result+=("- " + InsertAt.syntax + ": " + InsertAt.description + "\n");
        result+=("- " + PrintFieldDescendingCave.syntax + ": " + PrintFieldDescendingCave.description + "\n");
        result+=("- " + RemoveById.syntax + ": " + RemoveById.description + "\n");
        result+=("- " + RemoveLower.syntax + ": " + RemoveLower.description + "\n");
        result+=("- " + Reorder.syntax + ": " + Reorder.description + "\n");
        result+=("- " + Save.syntax + ": " + Save.description + "\n");
        result+=("- " + Show.syntax + ": " + Show.description + "\n");
        result+=("- " + Update.syntax + ": " + Update.description);

        return result;
    }
}
