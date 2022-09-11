package se.ifmo.programming.lab6;

import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.utils.Application;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        System.out.println(0);
        String fileName = System.getenv("FILENAME");
        /*if (fileName == null) {
            System.out.println("Переменная окружения не установлена.\nВыход из программы.");
            //System.exit(16);
        }
        File file = new File(fileName);
        System.out.println(3);
        if (!file.exists()) {
            System.out.println("Файл не найден!\nВыход из программы.");
            //System.exit(17);
        }
        else if (!file.canRead()) {
            System.out.println("Отсутствуют права на чтение!\nВыход из программы.");
            //System.exit(18);
        } else if (!file.canWrite()) {
            System.out.println("Отсутствуют права на запись!\nВыход из программы.");
            //System.exit(19);
        }
        System.out.println(658);
        DragonVectorStorage dragonVectorStorage = new DragonVectorStorage(file);
        Application application = new Application(dragonVectorStorage);
        //application.run();
         */

    }
}
