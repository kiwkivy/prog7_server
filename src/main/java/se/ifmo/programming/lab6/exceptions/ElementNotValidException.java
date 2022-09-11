package se.ifmo.programming.lab6.exceptions;

/**
 * Выбрасывается при попытки добавить в коллекцию некорректный элемент.
 */
public class ElementNotValidException extends Exception {

    public ElementNotValidException(final String message) {
        super(message);
    }

}
