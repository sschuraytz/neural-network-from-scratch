package andrewoid.neuralnetwork.digit_ui;

import andrewoid.neutralnetwork.Network;
import andrewoid.neutralnetwork.Neuron;
import java.util.Random;

public class DigitInteraction {

    public static void main(String[] args) {
        Network network = new Network(7, 2, 15, 15, 6, 10);
        trainNetwork(network);
        new DigitFrame(network).setVisible(true);
    }

    private static void trainNetwork(Network network) {
        double inputs[][] = new double[][]{
                {1, 1, 1, 0, 1, 1, 1}, // 0
                {0, 0, 1, 0, 0, 1, 0}, // 1
                {1, 0, 1, 1, 1, 0, 1}, // 2
                {1, 0, 1, 1, 0, 1, 1}, // 3
                {0, 1, 1, 1, 0, 1, 0}, // 4
                {1, 1, 0, 1, 0, 1, 1}, // 5
                {1, 1, 0, 1, 1, 1, 1}, // 6
                {1, 0, 1, 0, 0, 1, 0}, // 7
                {1, 1, 1, 1, 1, 1, 1}, // 8
                {1, 1, 1, 1, 0, 1, 0}, // 9
        };

        double expectedOutputs[][] = new double[][] {
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 2
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 3
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // 4
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 5
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // 6
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // 7
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // 8
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
        };
        Neuron outputs[];
        Random random = new Random();

        // train the network on random input and output sets.
        for (int i = 0; i < 4000000; i++) {     //can't train much more without extremely long run time
            int set = random.nextInt(inputs.length);
            network.train(inputs[set], expectedOutputs[set], 0.3);
        }
    }
}
