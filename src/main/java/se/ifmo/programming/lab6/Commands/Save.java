package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.utils.FileWorker;

import java.io.File;

/**
 * Команда save : сохранить коллекцию в файл.
 */

public class Save extends Command{
    private transient FileWorker worker;
    private transient File file;
    private transient DragonVectorStorage dragonVectorStorage;
    public static String description = "сохранить коллекцию в файл";
    public static String syntax = "save";
    public CommandType commandType = CommandType.EXIT;

    public Save(DragonVectorStorage dragonVectorStorage, FileWorker worker, File file) {
        this.dragonVectorStorage = dragonVectorStorage;
        this.worker = worker;
        this.file = file;
    }

    public Save() {
        this.dragonVectorStorage = Server.dragonVectorStorage;
        this.worker = new FileWorker();
        this.file = new File(System.getenv("FILENAME"));

    }

    public CommandType getCommandList() {return commandType;}

    @Override
    public String execute() {
        dragonVectorStorage.save(dragonVectorStorage, file, worker);
        return "";
    }
}
