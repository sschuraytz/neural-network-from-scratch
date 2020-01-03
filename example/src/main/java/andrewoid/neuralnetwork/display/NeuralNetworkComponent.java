package andrewoid.neuralnetwork.display;

import andrewoid.neutralnetwork.Neuron;
import andrewoid.neutralnetwork.Network;

import javax.swing.*;
import java.awt.*;


public class NeuralNetworkComponent extends JComponent {
    int start = 25;
    int size = 10;
    int space = 200;
    int space2 = 50;
    Network network;
    Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK};

    public NeuralNetworkComponent(Network network) {
        this.network = network;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color neuronColor = Color.BLACK;
        int neuronValue;
        int pathWeight;
        Color pathColor = Color.BLACK;
        Neuron[][] layers = network.getLayers();
        for (int i = 0; i < layers.length; i++) {
            for (int j = 0; j < layers[i].length; j++) {
                neuronValue = (int) layers[i][j].getValue();
                neuronColor = colors[neuronValue];
                drawNeuron(i, j, neuronColor, g);
                for (int k = 0; k < layers[i][j].getNumWeights(); k++) {
                    pathWeight = (int) layers[i][j].getWeight(k);
                    pathColor = colors[pathWeight];
                    drawConnection(i, j, k, pathColor, g);
                }
            }
        }
    }

    private void drawNeuron(int row, int column, Color color, Graphics g) {
        g.setColor(color);
        g.drawOval(start + (row * space),
                start + (column * space2),
                size,
                size);
    }

    private void drawConnection(int row, int column, int number, Color color, Graphics g) {
        g.setColor(color);
        g.drawLine(start + ((row - 1) * space) + size,
                start + (number * space2) + (int) (size / 2),
                start + ((row) * space),
                start + (column * space2) + (int) (size / 2));
    }
}
