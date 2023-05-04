package org.example;

import BusinessLogic.Controller;
import GUI.SimulationFrame;
import Model.*;

public class Main {
    public static void main(String[] args) {
        SimulationFrame simFrame = new SimulationFrame();
        Controller c = new Controller(simFrame);
    }
}