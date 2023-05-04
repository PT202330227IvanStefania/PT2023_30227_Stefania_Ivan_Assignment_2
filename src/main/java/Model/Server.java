package Model;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.exit;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server() {
        //initialize queue and waiting period
        this.tasks = new ArrayBlockingQueue<>(100);
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask) {
        //add task to queue and increment the waiting period
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    @Override
    public void run() {
        while (true) {
            //take next task from queue
            //stop the thread for a time equal with the task's processing time
            //decrement the waiting period
            if (!tasks.isEmpty()) {
                //Task currentTask = tasks.peek();
                while(tasks.peek().getServiceTime() != 0) {
                    try {
                        Thread.sleep(1000);
                        tasks.peek().setServiceTime(tasks.peek().getServiceTime() - 1);
                        waitingPeriod.getAndDecrement();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(tasks.peek().getServiceTime() * 1000);
                }catch(InterruptedException e) {
                    exit (-1);
                }
                if (tasks.size() > 0) {
                    waitingPeriod.getAndAdd(-tasks.peek().getServiceTime());
                    if (tasks.peek().getServiceTime() == 0) {
                        try {
                            tasks.take();
                        } catch (InterruptedException ex) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public Task[] getTasks() {
            return tasks.toArray(new Task[0]);
    }

    public String toString(){
        String result ="";
        result += "  Waiting period: " + getWaitingPeriod() + "\n";
        result += "  Tasks:\n";
        Iterator<Task> iterator = tasks.iterator();
        while(iterator.hasNext()) {
            Task t = iterator.next();
            result += "  " + t.toString() + "\n";

        }
        return result;
    }

    public static void main(String[] args){
        Server s = new Server();
        Thread t = new Thread();
        t.start();
    }
}

