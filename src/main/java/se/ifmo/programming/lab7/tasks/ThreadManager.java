package se.ifmo.programming.lab7.tasks;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadManager extends Thread{
    private ForkJoinPool answerExecutor;
    private ThreadPoolExecutor handleExecutor;
    private ForkJoinPool receiveExecutor;

    private ArrayList<AnswerTask> answers = new ArrayList<>();
    private ArrayList<HandleTask> handles = new ArrayList<>();
    private ArrayList<ReceiveTask> receives = new ArrayList<>();

    private boolean autoAddingFlag = true;
    public static boolean sudoFlag = false;

    public static int activeAnswerTasks = 0, activeHandleTasks = 0, activeReceivesTask = 0;

    public ThreadManager() {
        answerExecutor = new ForkJoinPool();
        handleExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        receiveExecutor = new ForkJoinPool();
    }


    @Override
    public void run() {
        Thread handleThread = new Thread(() -> {
            while (!handleExecutor.isShutdown()) {
                if (handles.size() != 0) {
                    synchronized (receives) {
                        handleExecutor.execute(handles.get(handles.size() - 1));
                        handles.remove(handles.size() - 1);
                        activeHandleTasks++;
                    }
                }
            }
        });
        Thread receiveThread = new Thread(() -> {
            while (!receiveExecutor.isShutdown()) {
                if (receives.size() != 0) {
                    synchronized (receives) {
                        receiveExecutor.execute(receives.get(receives.size() - 1));
                        receives.remove(receives.size() - 1);
                        activeReceivesTask++;
                        autoAddingFlag = true;
                    }
                }
            }
        });
        Thread answerThread = new Thread(() -> {
            while (!answerExecutor.isShutdown()) {
                if (answers.size() != 0) {
                    answerExecutor.execute(answers.get(answers.size() - 1));
                    answers.remove(answers.size() - 1);
                    activeAnswerTasks++;
                }
            }
        });

        receiveThread.start();
        handleThread.start();
        answerThread.start();
        if (sudoFlag)
            System.out.println("Threads are running");


        while (true && activeReceivesTask < 5) {
            while (activeReceivesTask < 5 && autoAddingFlag) {
                synchronized (receives) {
                    autoAddingFlag = false;
                    receives.add(new ReceiveTask());
                }
            }
        }
        while (true){
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            if(message.equals("sudo")) {
                sudoFlag = !sudoFlag;
                System.out.println("sudo: " + sudoFlag);
            }
            if(message.equals("exit") || message.equals("^D")){
                endThreadManager();
                System.exit(0);
            }

        }
    }

    public void endThreadManager() {
        answerExecutor.shutdown();
        handleExecutor.shutdown();
        receiveExecutor.shutdown();
    }

    public void addTask(Task task){
        if(task.getClass().equals(ReceiveTask.class))
            receives.add((ReceiveTask) task);
        else if(task.getClass().equals(HandleTask.class))
            handles.add((HandleTask) task);
        else
            answers.add((AnswerTask) task);
    }
}
