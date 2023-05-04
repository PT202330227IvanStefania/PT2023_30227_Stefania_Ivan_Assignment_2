package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;


public interface Strategy {
    public default void addTask(List<Server> servers, Task t){
    }

    public class ConcreteStrategyTime implements Strategy
    {

        @Override
        public void addTask(List<Server> servers, Task t) {
            Server minServer = servers.get(0); //initializez primul server din lista ca fiind serverul cu cel mai mic timp de asteptare
            for(Server s: servers){
                if(s.getWaitingPeriod().get() < minServer.getWaitingPeriod().get()){
                    minServer = s;
                }
            }
            minServer.addTask(t);  //am adaugat taskul la serverul cu cel mai mic timp de asteptare
        }
    }

    public class ShortestQueueStrategy implements Strategy{

    }

    public enum SelectionPolicy{
        SHORTEST_QUEUE, SHORTEST_TIME
    }
}
