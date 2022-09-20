package se.ifmo.programming.lab6;

import com.google.gson.*;
import com.google.gson.internal.bind.util.ISO8601Utils;
import se.ifmo.programming.lab6.Commands.*;
import se.ifmo.programming.lab6.data.Coordinates;
import se.ifmo.programming.lab6.data.Dragon;
import se.ifmo.programming.lab6.data.DragonCave;
import se.ifmo.programming.lab6.deserializers.CaveDeserializer;
import se.ifmo.programming.lab6.deserializers.CoordinatesDeserializer;
import se.ifmo.programming.lab6.deserializers.DragonDeserializer;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;
import se.ifmo.programming.lab6.utils.FileWorker;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Server {
    public static ArrayList<Command> listOfCommands = new ArrayList<>();
    public static DragonVectorStorage dragonVectorStorage;
    public static List<String> scriptArray = new ArrayList<>();
    public static boolean normalWork = true;

    public static void main(String[] args){
        String fileName = System.getenv("FILENAME");
        File file = new File(fileName);
        dragonVectorStorage = new DragonVectorStorage(file);
        FileWorker worker = new FileWorker();
        dragonVectorStorage = worker.readFile(dragonVectorStorage, dragonVectorStorage.getFile());

        getListOfCommands();
        while(true) {
            if (normalWork) {
                receiveMessage();
            }
        }
    }




    public static void sendMessage(String message){
        try {
            byte[] data = message.getBytes();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, 2222);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        }catch (IOException ex){
            System.out.println(1);
        }
    }

    public static void receiveMessage(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(1111);
            byte[] bytes = new byte[8192];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            Command command = deserializer(message);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            sendMessage(gson.toJson(command.execute()));
        } catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            socket.close();
        }
    }

    public static Command deserializer(String message){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Dragon.class, new DragonDeserializer())
                .registerTypeAdapter(DragonCave.class, new CaveDeserializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .create();
        Command result = null;

        for (Command command: listOfCommands){
            try {
                    result = gson.fromJson(message, command.getClass());
                if (result.getCommandList().equals(command.getCommandList())) {
                    return result;
                }
            } catch (JsonIOException ex){
                }
            }
        return null;
    }

    public static void getListOfCommands(){
        listOfCommands.add(new Add());
        listOfCommands.add(new Clear());
        listOfCommands.add(new ExecuteScript());
        listOfCommands.add(new FilterStartsWithName());
        listOfCommands.add(new Help());
        listOfCommands.add(new InsertAt());
        listOfCommands.add(new PrintFieldDescendingCave());
        listOfCommands.add(new RemoveById());
        listOfCommands.add(new RemoveLower());
        listOfCommands.add(new Reorder());
        listOfCommands.add(new Save());
        listOfCommands.add(new Show());
        listOfCommands.add(new Update());
        listOfCommands.add(new CountByColor());
        listOfCommands.add(new Info());
    }
}

