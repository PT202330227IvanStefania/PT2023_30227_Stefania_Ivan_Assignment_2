package GUI;

import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private JFrame frame;
    private JLabel t;
    private JLabel clients;
    private JLabel queues;
    private JLabel simulationInterval;
    private JLabel minArrivalTime;
    private JLabel maxArrivalTime;
    private JLabel minServiceTime;
    private JLabel maxServiceTime;
    private JTextField tfClients;
    private JTextField tfQueues;
    private JTextField tfSimulation;
    private JTextField tfMinArrival;
    private JTextField tfMaxArrival;
    private JTextField tfMinService;
    private JTextField tfMaxService;
    private JButton validateData;
    private TextArea text = new TextArea();

    public SimulationFrame(){
        frame = new JFrame("Queue Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);

        JPanel title = new JPanel();
        t = new JLabel("Queue Management System");
        t.setFont(new Font("Arial", Font.BOLD, 24));
        title.add(t);
        title.setLayout(new FlowLayout());

        JPanel labels = new JPanel();
        clients = new JLabel("Number of clients: ");
        clients.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        clients.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labels.add(clients);
        queues = new JLabel("Number of queues: ");
        queues.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        queues.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        labels.add(queues);
        simulationInterval = new JLabel("Simulation Interval: ");
        simulationInterval.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        simulationInterval.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
        labels.add(simulationInterval);
        minArrivalTime = new JLabel("Minimum Arrival Time: ");
        minArrivalTime.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        minArrivalTime.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
        labels.add(minArrivalTime);
        maxArrivalTime = new JLabel("Maximum Arrival Time: ");
        maxArrivalTime.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        maxArrivalTime.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
        labels.add(maxArrivalTime);
        minServiceTime = new JLabel("Minimum Service Time: ");
        minServiceTime.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        minServiceTime.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
        labels.add(minServiceTime);
        maxServiceTime = new JLabel("Maximum Service Time: ");
        maxServiceTime.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        maxServiceTime.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
        labels.add(maxServiceTime);
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));

        JPanel textFields = new JPanel();
        tfClients = new JTextField(10);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        textFields.add(tfClients);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        tfQueues = new JTextField(10);
        textFields.add(tfQueues);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        tfSimulation = new JTextField(10);
        textFields.add(tfSimulation);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        tfMinArrival = new JTextField(10);
        textFields.add(tfMinArrival);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        tfMaxArrival = new JTextField(10);
        textFields.add(tfMaxArrival);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        tfMinService = new JTextField(10);
        textFields.add(tfMinService);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        tfMaxService = new JTextField(10);
        textFields.add(tfMaxService);
        textFields.add(Box.createRigidArea(new Dimension(60,5)));
        textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));

        JPanel middle = new JPanel();
        middle.add(labels);
        middle.add(textFields);
        middle.setBorder(BorderFactory.createEmptyBorder(10,0,10,60));
        middle.setLayout(new GridLayout(1,2));

        JPanel button = new JPanel();
        validateData = new JButton("Validate Data Input");
        validateData.setPreferredSize(new Dimension(300,50));
        validateData.setFont(new Font("Book Antiqua", Font.BOLD, 20));
        button.add(validateData);
        button.setLayout(new FlowLayout());

        JPanel tf = new JPanel();
        tf.setSize(new Dimension(200,200));
        tf.add(text);
        tf.setLayout(new BoxLayout(tf, BoxLayout.Y_AXIS));

        JPanel finalPanel = new JPanel();
        finalPanel.add(title);
        finalPanel.add(middle);
        finalPanel.add(tf);
        finalPanel.add(button);
        finalPanel.setLayout(new GridLayout(4,1));

        JPanel p = new JPanel();
        p.add(finalPanel);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        frame.setContentPane(p);
        frame.setVisible(true);
    }

    public void addValidateButton(ActionListener actionListener){
        this.validateData.addActionListener(actionListener);
    }

    public String getMinArrivalTime(){
        return this.tfMinArrival.getText();
    }

    public String getMaxArrivalTime(){
        return this.tfMaxArrival.getText();
    }

    public String getMinServiceTime(){
        return this.tfMinService.getText();
    }

    public String getMaxServiceTime(){
        return this.tfMaxService.getText();
    }

    public String getClients(){
        return this.tfClients.getText();
    }

    public String getQueues(){
        return this.tfQueues.getText();
    }

    public String getSimulationInterval(){
        return this.tfSimulation.getText();
    }

    public void showError(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void updateTf(String newValue){
        this.text.setText(newValue);
    }
}
