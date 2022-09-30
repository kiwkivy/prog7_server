package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.storage.Storage;

import java.util.Collection;

/**
 * Родительский класс для команд.
 */

public abstract class Command<T extends Collection<E>, E> {
    protected transient Storage<?, E> storage;
    protected transient E element;
    private transient String description;
    private transient String syntax;
    protected int port;
    protected String username;
    protected String password;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract CommandType getCommandType();


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
