package andrewoid.neuralnetwork.display;

import andrewoid.neutralnetwork.Network;
import andrewoid.neutralnetwork.Neuron;


import javax.swing.*;
import java.awt.*;

public class DisplayNeuralNetwork extends JFrame {

    public DisplayNeuralNetwork() {
        Network network = new Network(7, 15, 15, 10);
        final NeuralNetworkComponent neuralNetworkComponent = new NeuralNetworkComponent(network);
        setTitle("NeuralNetwork");
        setSize(1100, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        root.add(neuralNetworkComponent, BorderLayout.CENTER);
        setContentPane(root);
    }

    public static void main(String args[]) {
        DisplayNeuralNetwork frame = new DisplayNeuralNetwork();
        frame.setVisible(true);
    }
}
