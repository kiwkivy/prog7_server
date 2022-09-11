package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.DragonVectorStorage;

import java.util.Collection;

/**
 * Команда print_field_descending_cave : вывести значения поля cave всех элементов в порядке убывания.
 */

public class PrintFieldDescendingCave extends Command {
    DragonVectorStorage dragonVectorStorage;
    public static String description = "вывести значения поля cave всех элементов в порядке убывания";
    public static String syntax = "print_field_descending_cave";
    public CommandType commandType = CommandType.PRINT_FIELD_DESCENDING_CAVE;

    public PrintFieldDescendingCave(DragonVectorStorage dragonVectorStorage) {
        this.dragonVectorStorage = dragonVectorStorage;
    }

    public PrintFieldDescendingCave() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute(){
        System.out.println("Значения поля cave всех элементов в порядке убывания:");
        Collection dragonCaveCollection = dragonVectorStorage.getAllDescendingCave();
        for (Object dragonCave : dragonCaveCollection){
            System.out.println(dragonCave.toString());
        }
        return null;
    }
}
