package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.storage.Storage;

/**
 * Команда clear : очистить коллекцию.
 */
public class Clear extends Command {
    private String description = "очистить ваших драконов из коллекции";
    private String syntax = "clear";
    public CommandType commandType = CommandType.CLEAR;

    public Clear(Storage storage) {
        super(storage);
    }

    public Clear() {
    }
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    @Override
    public String execute(){
        Server.databaseWorker.clearDragons(username);
        Server.dragonVectorStorage = Server.databaseWorker.loadCollectionFromDB();
        return "Ваши драконы очищены.";
    }

}
