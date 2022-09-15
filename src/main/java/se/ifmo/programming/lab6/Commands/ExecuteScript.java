package se.ifmo.programming.lab6.Commands;

import se.ifmo.programming.lab6.Server;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.utils.Interpreter;

import java.io.File;

/**
 * Команда execute_script file_name : считать и исполнить скрипт из указанного файла.
 * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScript extends Command {
    private transient DragonVectorStorage dragonVectorStorage;
    private transient File scriptFile;
    public String fileName;
    public CommandType commandType = CommandType.EXECUTE_SCRIPT;

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
        String result;
        File scriptFile = new File(fileName);
        result = ("Выполнение скрипта " + scriptFile);
        Interpreter interpreter = new Interpreter(dragonVectorStorage, scriptFile);
        interpreter.start(result);
        return null;
    }

}
