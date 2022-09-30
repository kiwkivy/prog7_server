package se.ifmo.programming.lab7;

import com.google.gson.*;
import se.ifmo.programming.lab7.Commands.*;
import se.ifmo.programming.lab7.data.*;
import se.ifmo.programming.lab7.deserializers.CaveDeserializer;
import se.ifmo.programming.lab7.deserializers.CoordinatesDeserializer;
import se.ifmo.programming.lab7.deserializers.DragonDeserializer;
import se.ifmo.programming.lab7.storage.DragonVectorStorage;
import se.ifmo.programming.lab7.tasks.ThreadManager;
import se.ifmo.programming.lab7.utils.DatabaseWorker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class Server {
    public static ArrayList<Command> listOfCommands = new ArrayList<>();
    public static DragonVectorStorage dragonVectorStorage = new DragonVectorStorage();
    public static List<String> scriptArray = new ArrayList<>();
    public static DatabaseWorker databaseWorker;
    public static ThreadManager threadManager = new ThreadManager();
    public static DatagramSocket receiver;
    public static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Dragon.class, new DragonDeserializer())
            .registerTypeAdapter(DragonCave.class, new CaveDeserializer())
            .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
            .create();


    public static void main(String[] args) {
        connectToDatabase();
        Dragon dragon = new Dragon("45", new Coordinates(57l, 543d), 1, Color.BLACK,
                DragonType.AIR, DragonCharacter.WISE,
                new DragonCave(45l, 46d));
        getListOfCommands();
        try {
            receiver = new DatagramSocket(3321);
            threadManager.run();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        receiver.close();
        }

    }

    private static void connectToDatabase() {
        String username = null;
        String password = null;
        Scanner credentials = null;
        Scanner scanner = new Scanner(System.in);
        try {
            credentials = new Scanner(new FileReader("src/main/java/credentials.txt"));
            //credentials = new Scanner(new FileReader("credentials.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Файл 'credentials.txt' не найден.");
            System.exit(-7);
        }
        try {
            username = credentials.nextLine().trim();
            password = credentials.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.err.println("Данные для выхода отсутствуют. Выход из программы.");
            System.exit(-10);
        }

        String URL = "jdbc:postgresql://127.0.0.1:8888/studs";
        //String URL = "jdbc:postgresql://127.0.0.1:5432/studs";
        databaseWorker = new DatabaseWorker(URL, username, password);
        databaseWorker.connectToDatabase();

        dragonVectorStorage = databaseWorker.loadCollectionFromDB();
    }

    public static void sendMessage(String message, int port) {
        try {
            byte[] data = message.getBytes();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        } catch (IOException ex) {
        }
    }

    public static String receiveMessage(DatagramSocket receiver) {
        String message = null;
        try {
            synchronized (receiver) {
                byte[] bytes = new byte[8192];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                receiver.receive(packet);
                message = new String(packet.getData(), 0, packet.getLength());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }


    public static Command deserializer(String message) {
        gson = new GsonBuilder()
                .registerTypeAdapter(Dragon.class, new DragonDeserializer())
                .registerTypeAdapter(DragonCave.class, new CaveDeserializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .create();
        Command result;

        for (Command command : listOfCommands) {
            try {
                result = gson.fromJson(message, command.getClass());
                if (result.getCommandType().equals(command.getCommandType())) {
                    return result;
                }
            } catch (JsonIOException ex) {
            }
        }
        return null;
    }

    public static void getListOfCommands() {
        listOfCommands.add(new Add());
        listOfCommands.add(new Start());
        listOfCommands.add(new Clear());
        listOfCommands.add(new ExecuteScript());
        listOfCommands.add(new FilterStartsWithName());
        listOfCommands.add(new Help());
        listOfCommands.add(new PrintFieldDescendingCave());
        listOfCommands.add(new RemoveById());
        listOfCommands.add(new RemoveLower());
        listOfCommands.add(new Reorder());
        listOfCommands.add(new Show());
        listOfCommands.add(new Update());
        listOfCommands.add(new CountByColor());
        listOfCommands.add(new Info());
    }

}

