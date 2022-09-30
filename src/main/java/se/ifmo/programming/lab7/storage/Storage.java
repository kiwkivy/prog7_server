package se.ifmo.programming.lab7.storage;

import se.ifmo.programming.lab7.exceptions.ElementNotValidException;

import java.util.Collection;

/**
 * Интерфейс для коллекций c элементами любого типа.
 */
public interface Storage<T extends Collection<E>, E>{

    /**
     * Реализация команды add.
     */
    void add(final E element);

    /**
     * Реализация команды remove_by_id.
     */
    void removeById(final int id);


    /**
     * Реализация команды insert_at.
     */
    void insertAt(final int index, final E element) throws ElementNotValidException;

    /**
     * Реализация команды clear.
     */
    void clear();

    /**
     * Реализация команды reorder.
     */
    void reorder();

    /**
     * Реализация команды info.
     */
    String info();

    /**
     * Реализация команды show.
     */
    String show();

    /**
     * Реализация команды filter_starts_with_name.
     */
    String filterStartsWithName(String name);

    String getStringCollection();
}
