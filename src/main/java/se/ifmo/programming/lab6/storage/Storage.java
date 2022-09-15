package se.ifmo.programming.lab6.storage;

import se.ifmo.programming.lab6.exceptions.ElementNotValidException;

import java.util.Collection;

/**
 * Интерфейс для коллекций c элементами любого типа.
 */
public interface Storage<T extends Collection<E>, E>{

    /**
     * Релизация команды add.
     */
    void add(final E element);

    /**
     * Релизация команды remove_by_id.
     */
    void removeById(final int id);

    /**
     * Релизация команды update.
     */
    void update(final int id, final E element) throws ElementNotValidException;

    /**
     * Релизация команды insert_at.
     */
    void insertAt(final int index, final E element) throws ElementNotValidException;

    /**
     * Релизация команды remove_lower;
     */
    void removeLower(int id);

    /**
     * Релизация команды clear.
     */
    void clear();

    /**
     * Релизация команды reorder.
     */
    void reorder();

    /**
     * Релизация команды info.
     */
    String info();

    /**
     * Релизация команды show.
     */
    String show();

    /**
     * Релизация команды filter_starts_with_name.
     */
    String filterStartsWithName(String name);

    String getStringCollection();
}
