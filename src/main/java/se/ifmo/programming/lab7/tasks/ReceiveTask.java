package se.ifmo.programming.lab7.tasks;

import se.ifmo.programming.lab7.Commands.Command;
import se.ifmo.programming.lab7.Server;

import java.net.DatagramSocket;

public class ReceiveTask extends Task {
    private int port;

    public void ReceiveTask(int port){
        this.port = port;
        if(ThreadManager.sudoFlag)
            System.out.println("Receive task added");
    }
    @Override
    public void run() {
        DatagramSocket receiver = Server.receiver;
        String result = "";
        while (result.equals("")) {
            result = Server.receiveMessage(receiver);
        }
        if(ThreadManager.sudoFlag)
            System.out.println(result);
        Command command = null;
        command = Server.deserializer(result);
        Server.threadManager.addTask(new ReceiveTask());
        Server.threadManager.addTask(new HandleTask(command));
    }
}
