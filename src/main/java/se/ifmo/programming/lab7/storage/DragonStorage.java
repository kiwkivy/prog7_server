package se.ifmo.programming.lab7.storage;

import se.ifmo.programming.lab7.data.Color;
import se.ifmo.programming.lab7.data.Dragon;

import java.util.Collection;

/**
 * Интерфейс для коллекций с элементами типа Dragon.
 */
public interface DragonStorage<T extends Collection<Dragon>> extends Storage<T, Dragon> {

    /**
     * Релизация команды count_by_color.
     */
    String countByColor(final Color color);
}
