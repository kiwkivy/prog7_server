package se.ifmo.programming.lab6.Commands;

/**
 * Комманда help : вывести справку по доступным командам.
 */

public class Help extends Command{
    public static String description = "вывести справку по доступным командам";
    public static String syntax = "help";
    public CommandType commandType = CommandType.HELP;

    public Help() {
    }
    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        System.out.println("Вывод справки:");
        System.out.println("- " + Add.syntax + ": " + Add.description);
        System.out.println("- " + Clear.syntax + ": " + Clear.description);
        System.out.println("- " + CountByColor.syntax + ": " + CountByColor.description);
        System.out.println("- " + ExecuteScript.syntax + ": " + ExecuteScript.description);
        System.out.println("- " + FilterStartsWithName.syntax + ": " + FilterStartsWithName.description);
        System.out.println("- " + Help.syntax + ": " + Help.description);
        System.out.println("- " + Info.syntax + ": " + Info.description);
        System.out.println("- " + InsertAt.syntax + ": " + InsertAt.description);
        System.out.println("- " + PrintFieldDescendingCave.syntax + ": " + PrintFieldDescendingCave.description);
        System.out.println("- " + RemoveById.syntax + ": " + RemoveById.description);
        System.out.println("- " + RemoveLower.syntax + ": " + RemoveLower.description);
        System.out.println("- " + Reorder.syntax + ": " + Reorder.description);
        System.out.println("- " + Save.syntax + ": " + Save.description);
        System.out.println("- " + Show.syntax + ": " + Show.description);
        System.out.println("- " + Update.syntax + ": " + Update.description);
        return null;
    }
}
