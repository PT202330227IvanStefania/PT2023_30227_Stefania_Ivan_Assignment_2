package BusinessLogic;

import GUI.SimulationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private SimulationFrame frame;

    public SimulationManager initializeSimulation(){
        int numberOfClients = 0;
        int numberOfServers = 0 ;
        int timeLimit =0 ;
        int minArrivalTime = 0;
        int maxArrivalTime = 0;
        int minProcessingTime = 0;
        int maxProcessingTime = 0;

        try{
            numberOfClients = Integer.parseInt(frame.getClients());
            numberOfServers = Integer.parseInt(frame.getQueues());
            timeLimit = Integer.parseInt(frame.getSimulationInterval());
            minArrivalTime = Integer.parseInt(frame.getMinArrivalTime());
            maxArrivalTime = Integer.parseInt(frame.getMaxArrivalTime());
            minProcessingTime = Integer.parseInt(frame.getMinServiceTime());
            maxProcessingTime = Integer.parseInt(frame.getMaxServiceTime());
            System.out.println("Numberofc " + numberOfClients);
        } catch(NumberFormatException ex){
            frame.showError("Wrong type of input!");
        }
        return new SimulationManager(numberOfClients, numberOfServers, timeLimit, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime, frame);
    }
    public Controller(SimulationFrame frame){
        this.frame = frame;
        this.frame.addValidateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimulationManager simulationManager = initializeSimulation();
                Thread t = new Thread(simulationManager);
                t.start();
            }
        });
    }


}
