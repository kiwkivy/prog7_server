package se.ifmo.programming.lab7.data;

import java.util.Scanner;

/**
 * Класс для создания объектов типа Dragon
 */
public class CreatorOfDragons {
    /**
     * Метод создаёт объект типа Dragon на основе значений из файла.
     *
     * @return dragon
     */
    public static Dragon create(Scanner scanner) {
        try {
            String name = scanner.nextLine();
            Long x = Long.parseLong(scanner.nextLine());
            Double y = Double.parseDouble(scanner.nextLine());
            Coordinates coordinates = new Coordinates(x, y);
            int age = Integer.parseInt(scanner.nextLine());
            Color color = Color.valueOf(scanner.nextLine());
            DragonType type = DragonType.valueOf(scanner.nextLine());
            DragonCharacter character = DragonCharacter.valueOf(scanner.nextLine());
            Double numberOfTreasures = Double.parseDouble(scanner.nextLine());
            Long depth = Long.parseLong(scanner.nextLine());
            DragonCave cave = new DragonCave(depth, numberOfTreasures);
            Dragon dragon = new Dragon(name, coordinates, age, color, type, character, cave);
            return dragon;
        }catch (Exception e){
            return null;
        }
    }

}
