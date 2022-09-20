package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.storage.Storage;

import java.util.Collection;

/**
 * Родительский класс для команд.
 */

public abstract class Command<T extends Collection<E>, E> {
    protected transient Storage<?, E> storage;
    protected transient E element;
    private transient String description;
    private transient String syntax;
    private int port;

    public int getPort() {
        return port;
    }

    public String getDescription() {
        return description;
    }

    public String getSyntax() {
        return syntax;
    }

    public Command(){
        storage = (Storage<?, E>) Server.dragonVectorStorage;
    }

    public abstract CommandType getCommandList();


    public Command(Storage<?, E> storage) {
        this.storage = storage;
    }

    public Command(Storage<?, E> storage, E element) {
        this.storage = storage;
        this.element = element;
    }

    /**
     * Выполнение команды
     */
    public abstract String execute();
}
