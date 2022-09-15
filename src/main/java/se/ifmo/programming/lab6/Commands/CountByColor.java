package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.data.Color;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;

/**
 * Команда count_by_color color : вывести количество элементов, значение поля color которых равно заданному.
 */

public class CountByColor extends Command {
    private Color color;
    public static String description = "вывести количество элементов, значение поля color которых равно заданному";
    public static String syntax = "count_by_color color";
    public CommandType commandType = CommandType.COUNT_BY_COLOR;

    public CountByColor() {
    }
    public CommandType getCommandList() {
        return commandType;
    }

    @Override
    public String execute() {
        return Server.dragonVectorStorage.countByColor(color);
    }
}
