package se.ifmo.programming.lab6.data;

import se.ifmo.programming.lab6.utils.Interpreter;

import java.util.Scanner;

/**
 * Класс для создания объектов типа Dragon
 */
public class CreatorOfDragons {

    /**
     * Метод создаёт объект типа Dragon на основе пользовательского ввода.
     *
     * @return dragon
     */
    public static Dragon create() {
        boolean isWrong = true;
        Scanner scanner = new Scanner(System.in);
        String data = "";
        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (!data.equals("")) {
                isWrong = false;
            }
        }
        String name = data;
        isWrong = true;

        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkLong(data)) {
                isWrong = false;
            }
        }
        Long x = Long.parseLong(data);
        isWrong = true;

        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkDouble(data)) {
                isWrong = false;
            }
        }
        Double y = Double.parseDouble(data);
        isWrong = true;

        Coordinates coordinates = new Coordinates(x, y);

        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkIntUpZero(data)) {
                isWrong = false;
            }
        }
        int age = Integer.parseInt(data);
        isWrong = true;

        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkColor(data)) {
                isWrong = false;
            }
        }
        Color color = Color.valueOf(data);
        isWrong = true;

        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkType(data)) {
                isWrong = false;
            }
        }
        DragonType type = DragonType.valueOf(data);
        isWrong = true;


        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkCharacter(data)) {
                isWrong = false;
            }
        }
        DragonCharacter character = DragonCharacter.valueOf(data);
        isWrong = true;

        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkDoublePositive(data)) {
                isWrong = false;
            }
        }
        Double numberOfTreasures = Double.parseDouble(data);
        isWrong = true;

        while (isWrong) {
            Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkDouble(data)) {
                isWrong = false;
            }
        }
        Long depth = Long.parseLong(data);

        DragonCave cave = new DragonCave(depth, numberOfTreasures);
        Dragon dragon = new Dragon(name, coordinates, age, color, type, character, cave);
        return dragon;
    }

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
