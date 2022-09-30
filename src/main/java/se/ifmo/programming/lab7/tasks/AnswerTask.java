package se.ifmo.programming.lab7.tasks;

import se.ifmo.programming.lab7.Server;

public class AnswerTask extends Task{
    private String message;
    private int port;
    public AnswerTask(String message, int port) {
        if(ThreadManager.sudoFlag)
            System.out.println("Answer task added");
        this.message = message;
        this.port = port;
    }

    @Override
    public void run() {
        Server.sendMessage(message, port);
        ThreadManager.activeAnswerTasks--;
    }
}
