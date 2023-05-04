package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        //for maxNoServers
        //create server object
        //create thread with the object
        //trebuie create obiecte de tip server si pornit un thread pt fiecare
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<>();
        for(int i = 0; i < maxNoServers; i++){
            Server server = new Server();
            Thread serverThread = new Thread(server);
            servers.add(server);
            serverThread.start();
        }
    }

    public void changeStrategy(Strategy.SelectionPolicy policy){
        //apply strategy patter to instantiate the strategy with the concrete strategy corresponding to policy
    if(policy == Strategy.SelectionPolicy.SHORTEST_QUEUE){
        strategy = new Strategy.ShortestQueueStrategy();
    }
    if(policy == Strategy.SelectionPolicy.SHORTEST_TIME){
        strategy = new Strategy.ConcreteStrategyTime();
    }
    }

    public void dispatchTask(Task t){
        //call the strategy addTask method
        Strategy.ConcreteStrategyTime strategy = new Strategy.ConcreteStrategyTime();
        strategy.addTask(servers, t);
    }

    public List<Server> getServers(){
        return servers;
    }

    public String toString(){
        String result = "";
        for(int i = 0; i < servers.size(); i++){
            Server server = servers.get(i);
            result += "Queue " + (i+1) + ":\n";
            result += server.toString();
        }
        return result;
    }
}
