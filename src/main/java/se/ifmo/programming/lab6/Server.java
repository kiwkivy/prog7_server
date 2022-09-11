package se.ifmo.programming.lab6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import se.ifmo.programming.lab6.Commands.*;
import se.ifmo.programming.lab6.data.Coordinates;
import se.ifmo.programming.lab6.data.Dragon;
import se.ifmo.programming.lab6.data.DragonCave;
import se.ifmo.programming.lab6.deserializers.CaveDeserializer;
import se.ifmo.programming.lab6.deserializers.CoordinatesDeserializer;
import se.ifmo.programming.lab6.deserializers.DragonDeserializer;
import se.ifmo.programming.lab6.storage.DragonVectorStorage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {
    public static ArrayList<Command> listOfCommands = new ArrayList<>();
    public static DragonVectorStorage dragonVectorStorage;

    public static void main(String[] args){
        getListOfCommands();
        while(true) {
            receiveMessage();
        }
    }




    public static void sendMessage(String message){
        try {
            byte[] data = message.getBytes();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, 4357);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        }catch (IOException ex){
            System.out.println("Эксепшн"); //TODO эксепшн
        }
    }

    public static void receiveMessage(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(1683);
            byte[] bytes = new byte[8192];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Сообщение: " + message);
            Command command = deserializer(message);
            sendMessage(new Gson().toJson(command.execute()));
            System.out.println("Команда: " + command);
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
    }
}

