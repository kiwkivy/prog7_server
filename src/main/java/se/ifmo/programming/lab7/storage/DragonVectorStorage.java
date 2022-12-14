package se.ifmo.programming.lab7.storage;

import se.ifmo.programming.lab7.data.Color;
import se.ifmo.programming.lab7.data.Dragon;
import se.ifmo.programming.lab7.data.DragonCave;
import se.ifmo.programming.lab7.exceptions.ElementNotValidException;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс векторной коллекции с элементами типа Dragon.
 */
public class DragonVectorStorage implements DragonStorage<Vector<Dragon>> {

    private int idCounter;
    private final Vector<Dragon> dragonVector;
    private final ZonedDateTime creationDate;
    private File file;

    /**
     * @return idCounter
     */
    public int getIdCounter() {
        return idCounter;
    }

    /**
     * @return file
     */
    public File getFile(){
        return file;
    }

    public DragonVectorStorage(File file) {
        this.file = file;
        idCounter = 1;
        dragonVector = new Vector<>();
        creationDate = ZonedDateTime.now();
    }


    public DragonVectorStorage() {
        idCounter = 1;
        dragonVector = new Vector<>();
        creationDate = ZonedDateTime.now();
    }

    @Override
    public void add(final Dragon someElement){
        dragonVector.add(someElement);
        idCounter++;
    }


    /*public void update(final int id, final Dragon element) throws ElementNotValidException{
        if (!element.isValid()) {
            throw new ElementNotValidException("Element is not valid");
        }
        dragonVector.setElementAt(element, id);
    }*/

    @Override
    public void removeById(final int id) {
        boolean result = dragonVector.removeIf(dragon -> dragon.getId() == id);
        if (id > 0 && id <= idCounter) {
        }
    }

    @Override
    public void insertAt(final int index, final Dragon element) throws ElementNotValidException{
        if (!element.isValid()) {
            throw new ElementNotValidException("Element is not valid");
        }
        dragonVector.insertElementAt(element, index);
    }

    @Override
    public void clear() {
        dragonVector.clear();
    }

    @Override
    public void reorder() {
        Collections.reverse(dragonVector);
    }

    @Override
    public String countByColor(final Color color) {
        return "Количество элементов цвета " + color + ": " + dragonVector.stream()
                .filter(dragon -> Objects.equals(dragon.getColor(), color))
                .count();
    }


    @Override
    public String filterStartsWithName(final String name) {
        Vector<Dragon> newVector = dragonVector.stream().filter(dragon -> dragon.getName() != null)
                .filter(dragon -> dragon.getName().startsWith(name))
                .collect(Collectors.toCollection(Vector::new));
       return newVector.toString();

    }
    @Override
    public String getStringCollection() {
        return (dragonVector.stream().toString());
    }

    public String getAllDescendingCave() {
        return (String) dragonVector.stream().map(
                s -> s.getCave()).sorted(Comparator.reverseOrder()).map(DragonCave::toString)
                .reduce((s1, s2) -> s1 + s2).orElse("Коллекция пуста");


    }

    /**
     * @return dragonVector
     */
    public Vector<Dragon> getDragonVector() {
        return dragonVector;
    }

    /**
     * @return creationDate
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }


    @Override
    public String info() {
        String result="";
        result+=("Тип: DragonVectorStorage" + "\n");
        result+=("Дата инициализации: " + getCreationDate() + "\n");
        result+=("Количество элементов: " + dragonVector.size() + "\n");
        return result;
    }

    @Override
    public String show(){
        return dragonVector.stream().map(s->s.toString()).reduce((s1, s2) -> s1 + s2).orElse("Коллекция пуста");
    }


    /**
     * Метод для генерации новых id.
     */


}
