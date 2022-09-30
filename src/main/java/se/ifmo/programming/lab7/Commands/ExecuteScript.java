package se.ifmo.programming.lab7.Commands;

import se.ifmo.programming.lab7.Server;
import se.ifmo.programming.lab7.storage.DragonVectorStorage;
import se.ifmo.programming.lab7.utils.Interpreter;

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

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public String execute() {
        scriptFile = new File(fileName);
        if (scriptFile.exists()) {
            if (!scriptFile.canRead()){
                Server.sendMessage("Отсутствуют права на чтение!", getPort());
            } else if (!scriptFile.canWrite()) {
                Server.sendMessage("Отсутствуют права на запись!", getPort());
            }
            if (!Server.scriptArray.contains(fileName)) {
                Server.scriptArray.add(fileName);
                Command executeScript = new ExecuteScript(dragonVectorStorage, scriptFile);
                Server.scriptArray.remove(fileName);
            } else Server.sendMessage("Данный скрипт уже использован.", getPort());
        } else
            Server.sendMessage("Не удалось получить данные из файла. Проверьте корректность данных.", getPort());
        //Server.normalWork = false;
        Scanner scanner = null;
        File scriptFile = new File(fileName);
        try {
            scanner = new Scanner(scriptFile);
        }catch (FileNotFoundException ex){
            Server.sendMessage("Ошибка доступа к файлу.", getPort());
        }
        Server.sendMessage("Выполнение скрипта " + scriptFile, getPort());
        Interpreter interpreter = new Interpreter(Server.dragonVectorStorage, scriptFile);
        interpreter.start(scanner, getPort());
        //Server.normalWork = true;
        return "Возврат в обычный режим.";
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
