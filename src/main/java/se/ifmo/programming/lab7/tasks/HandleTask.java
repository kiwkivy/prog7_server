package se.ifmo.programming.lab7.tasks;

import se.ifmo.programming.lab7.Commands.Command;
import se.ifmo.programming.lab7.Server;

public class HandleTask extends Task{
    private Command command;
    public HandleTask(Command command) {
        if(ThreadManager.sudoFlag)
            System.out.println("Hadle task added");
        this.command = command;
    }

    @Override
    public void run() {
        String result = null;
        if(command != null) {
            result = command.execute();
        }
        Server.threadManager.addTask(new AnswerTask(result, command.getPort()));
        ThreadManager.activeHandleTasks--;
    }
}
