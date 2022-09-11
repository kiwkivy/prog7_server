package se.ifmo.programming.lab6.utils;

import se.ifmo.programming.lab6.storage.DragonVectorStorage;

import java.util.ArrayList;

/**
 * Класс, запускающий приложение.
 */

public class Application {
    DragonVectorStorage dragonVectorStorage;

    public Application(DragonVectorStorage dragonVectorStorage){
        this.dragonVectorStorage = dragonVectorStorage;
    }

    /**
     * Метод для запуска приложения.
     */
    public void run(){
        if (dragonVectorStorage.getFile().length() != 0) {
            FileWorker worker = new FileWorker();
            dragonVectorStorage = worker.readFile(dragonVectorStorage, dragonVectorStorage.getFile());
        }
        Interpreter consoleInterpreter = new Interpreter(dragonVectorStorage);
        consoleInterpreter.start();
    }
}