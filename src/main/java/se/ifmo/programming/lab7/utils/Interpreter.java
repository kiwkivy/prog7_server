package se.ifmo.programming.lab7.utils;

import se.ifmo.programming.lab7.Commands.*;
import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.data.*;
import se.ifmo.programming.lab7.storage.DragonVectorStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter {
    private File file;
    private DragonVectorStorage dragonVectorStorage;
    public static List<String> scriptArray = new ArrayList<>();
    public static ArrayList<Command> listOfCommands = new ArrayList<>();

    public Interpreter(DragonVectorStorage dragonVectorStorage, File file) {
        this.dragonVectorStorage = dragonVectorStorage;
        this.file = file;
    }

    /**
     * Метод, реализующий работу интерпретатора. Интерпретатор работает в двух режимах: консольном и скриптовом.
     */

    public String start(Scanner scanner, int port) {
        String data = "";
        Dragon dragon;
        String resultOfCommand;
        while (true) {
            resultOfCommand ="";
            if (!scanner.hasNextLine() || ((data = scanner.nextLine()).equals(" "))){
                return ("Обнаружен конец файла.");
            }
            String[] commandParts = data.split("\\s+");
            String command = commandParts[0];
            if (!data.equals("")) {
                if (commandParts.length == 1) {
                    switch (command) {
                        case "clear":
                            Command clear = new Clear(dragonVectorStorage);
                            resultOfCommand =clear.execute();
                            break;
                        case "help":
                            Command help = new Help();
                            resultOfCommand =help.execute();
                            break;
                        case "info":
                            Command info = new Info(dragonVectorStorage);
                            resultOfCommand =info.execute();
                            break;
                        case "reorder":
                            Command reorder = new Reorder(dragonVectorStorage);
                            resultOfCommand =reorder.execute();
                            break;
                        case "show":
                            Command show = new Show(dragonVectorStorage);
                            resultOfCommand =show.execute();
                            break;
                        case "print_field_descending_cave":
                            Command printFieldDescendingCave = new PrintFieldDescendingCave();
                            resultOfCommand = printFieldDescendingCave.execute();
                            break;
                        case "add":
                            if (dragonVectorStorage.getIdCounter() <= 99) {
                                dragon = CreatorOfDragons.create(scanner);
                                if (dragon != null && dragon.isValid()) {
                                    Command add = new Add(dragonVectorStorage, dragon);
                                    resultOfCommand =add.execute();
                                }
                            }
                            break;
                        default:
                            break;
                    }
                } else if (commandParts.length == 2) {
                    switch (command) {
                        case "update":
                            if (dragonVectorStorage.getIdCounter() > Integer.parseInt(commandParts[1])) {
                                dragon = CreatorOfDragons.create(scanner);
                                if (dragon != null && dragon.isValid()) {
                                    Command update = new Update(
                                            dragonVectorStorage,
                                            dragon,
                                            Integer.parseInt(commandParts[1])
                                    );
                                    resultOfCommand = update.execute();
                                }
                            }
                            break;
                        case "count_by_color":
                            if (Checker.checkColor(commandParts[1])) {
                                Color color = Color.valueOf(commandParts[1]);
                                Command countByColor = new CountByColor(color);
                                resultOfCommand =countByColor.execute();
                            }
                            break;
                        case "execute_script":
                            File scriptFile = new File(commandParts[1]);
                            if (scriptFile.exists()) {
                                if (!scriptArray.contains(commandParts[1])) {
                                    scriptArray.add(commandParts[1]);
                                    Command executeScript = new ExecuteScript(dragonVectorStorage, scriptFile);
                                    resultOfCommand = executeScript.execute();
                                    scriptArray.remove(commandParts[1]);
                                }
                                resultOfCommand = "Данный скрипт уже использован.";
                            }
                            break;
                        case "filter_starts_with_name":
                            Command filterStartsWithName = new FilterStartsWithName(
                                    dragonVectorStorage,
                                    commandParts[1]
                            );
                            resultOfCommand = filterStartsWithName.execute();
                            break;
                        case "remove_by_id":
                            if (Checker.checkIntUpZero(commandParts[1])) {
                                Command removeById = new RemoveById(
                                        dragonVectorStorage,
                                        Integer.parseInt(commandParts[1])
                                );
                                resultOfCommand = removeById.execute();
                            }
                            break;
                        case "remove_lower":
                                RemoveLower removeLower = new RemoveLower<>();
                                resultOfCommand = removeLower.execute();
                        default:
                            break;
                    }
                }
            }
            Server.sendMessage(resultOfCommand, port);
        }
    }
}
