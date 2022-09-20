package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.utils.Interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Команда execute_script file_name : считать и исполнить скрипт из указанного файла.
 * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScript extends Command {
    private transient DragonVectorStorage dragonVectorStorage;
    private transient File scriptFile;
    private String fileName;
    private CommandType commandType = CommandType.EXECUTE_SCRIPT;

    public static String description = "считать и исполнить скрипт из указанного файла." +
            " В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    public static String syntax = "execute_script file_name";

    public ExecuteScript(){

    }

    public ExecuteScript(DragonVectorStorage dragonVectorStorage, File scriptFile){
        this.dragonVectorStorage = dragonVectorStorage;
        this.scriptFile = scriptFile;
    }

    public CommandType getCommandList() {
        return commandType;
    }

    @Override
    public String execute() {
        scriptFile = new File(fileName);
        if (scriptFile.exists()) {
            if (!scriptFile.canRead()){
                return "Отсутствуют права на чтение!";
            } else if (!scriptFile.canWrite()) {
                return "Отсутствуют права на запись!";
            }
            if (!Server.scriptArray.contains(fileName)) {
                Server.scriptArray.add(fileName);
                Command executeScript = new ExecuteScript(dragonVectorStorage, scriptFile);
                Server.scriptArray.remove(fileName);
            } else return "Данный скрипт уже использован.";
        } else
            return "Не удалось получить данные из файла. Проверьте корректность данных.";
        //Server.normalWork = false;
        Scanner scanner;
        File scriptFile = new File(fileName);
        try {
            scanner = new Scanner(scriptFile);
        }catch (FileNotFoundException ex){
            return "Ошибка доступа к файлу.";
        }
        Server.sendMessage("Выполнение скрипта " + scriptFile, getPort());
        Interpreter interpreter = new Interpreter(Server.dragonVectorStorage, scriptFile);
        interpreter.start(scanner, getPort());
        //Server.normalWork = true;
        return "Скрипт выполнен.";
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
