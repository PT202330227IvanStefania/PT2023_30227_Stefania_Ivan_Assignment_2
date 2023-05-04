package BusinessLogic;

import GUI.SimulationFrame;
import Model.Task;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class SimulationManager implements Runnable{
    public int timeLimit = 60;
    public int maxProcessingTime = 4;
    public int minProcessingTime = 2;
    public int numberOfServers = 3;
    public int numberOfClients = 4;
    public int minArrivalTime = 2;
    public int maxArrivalTime = 30;
    public Strategy.SelectionPolicy selectionPolicy = Strategy.SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks= new ArrayList<>();
    private Random random = new Random();

    public SimulationManager(int numberOfClients, int numberOfServers, int timeLimit, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, SimulationFrame frame){
        this.frame = frame;
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers ;
        this.timeLimit = timeLimit ;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        Scheduler scheduler = new Scheduler(numberOfServers, numberOfClients);
        generatedTasks = new ArrayList<Task>();

    }

    private void generateNRandomTasks(){
        for(int i = 0; i < numberOfClients; i++){
            Task task = new Task();
            int timeArrival = random.nextInt((maxArrivalTime-minArrivalTime) + 1) + minArrivalTime;
            int timeService = random.nextInt((maxProcessingTime-minProcessingTime) + 1) + minProcessingTime;
            task.setArrivalTime(timeArrival);
            task.setServiceTime(timeService);
            generatedTasks.add(task);
        }
        Collections.sort(generatedTasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getArrivalTime() - o2.getArrivalTime();
            }
        });
    }

    @Override
    public void run() {
        try {
            PrintStream printStream = new PrintStream("log.txt");
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        generateNRandomTasks();
        String result = "";
        String clientsList = generatedTasks.toString();
        Scheduler scheduler = new Scheduler(numberOfServers, maxProcessingTime);
        scheduler.changeStrategy(Strategy.SelectionPolicy.SHORTEST_TIME);
        System.out.println(generatedTasks);
        int currentTime = 0;
        while(currentTime < timeLimit) {
            //iterator prin generated task
            Iterator<Task> iterator = generatedTasks.iterator();
            while(iterator.hasNext()){
                Task task = iterator.next();
                if(task.getArrivalTime() == currentTime){
                    scheduler.dispatchTask(task);
                    iterator.remove();
                }
            }
            //iterate generatedTasks list and pick tasks that have the arrivalTime equal with the current time
            //-send task to queue by calling the dispatch task method from scheduler
            //-delete client from list
            //update UI frame
            //frame.update(scheduler.getServers(),generatedTasks);
            result = "Current time " + currentTime + "\n" + scheduler.toString() + "\n";
            frame.updateTf(clientsList + "\n" + result);
            System.out.println("Current Time: " + currentTime + "\n");
            System.out.println(scheduler);
            currentTime++;
            try {
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }


}
