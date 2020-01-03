package andrewoid.neuralnetwork;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class DisplayNeuralNetwork extends JFrame {

    public DisplayNeuralNetwork() throws FileNotFoundException {
        Network network = new NetworkFactory().loadFromJSON("network.json");
        final NeuralNetworkComponent neuralNetworkComponent = new NeuralNetworkComponent(network);
        setTitle("NeuralNetwork");
        setSize(1100, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        root.add(neuralNetworkComponent, BorderLayout.CENTER);
        setContentPane(root);
    }

    public static void main(String args[]) throws FileNotFoundException {
        DisplayNeuralNetwork frame = new DisplayNeuralNetwork();
        frame.setVisible(true);
    }
}
