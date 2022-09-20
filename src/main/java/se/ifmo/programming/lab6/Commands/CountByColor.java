package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.data.Color;

/**
 * Команда count_by_color color : вывести количество элементов, значение поля color которых равно заданному.
 */

public class CountByColor extends Command {
    private Color color;
    private String description = "вывести количество элементов, значение поля color которых равно заданному";
    private String syntax = "count_by_color color";
    public CommandType commandType = CommandType.COUNT_BY_COLOR;

    public CountByColor() {
    }

    public CountByColor(Color color) {
        this.color = color;
    }
    public CommandType getCommandList() {
        return commandType;
    }

    @Override
    public String execute() {
        return Server.dragonVectorStorage.countByColor(color);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSyntax() {
        return syntax;
    }
}
