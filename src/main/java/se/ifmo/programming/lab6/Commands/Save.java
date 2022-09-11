package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.utils.FileWorker;

import java.io.File;

/**
 * Команда save : сохранить коллекцию в файл.
 */

public class Save extends Command{
    private FileWorker worker;
    private File file;
    private DragonVectorStorage dragonVectorStorage;
    public static String description = "сохранить коллекцию в файл";
    public static String syntax = "save";
    public CommandType commandType = CommandType.SAVE;

    public Save(DragonVectorStorage dragonVectorStorage, FileWorker worker, File file) {
        this.dragonVectorStorage = dragonVectorStorage;
        this.worker = worker;
        this.file = file;
    }

    public Save() {
    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        System.out.println("Коллекция сохранена в файл");
        dragonVectorStorage.save(dragonVectorStorage, file, worker);
        return null;
    }
}
