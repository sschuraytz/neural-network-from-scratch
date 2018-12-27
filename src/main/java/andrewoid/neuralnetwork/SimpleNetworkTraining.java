package andrewoid.neuralnetwork;

import java.util.Arrays;

/**
 * Demonstration to train a andrewoid.neuralnetwork.Network on random values.
 */
public class SimpleNetworkTraining {
    public static void main(String args[]) {
        Network network = new Network(4, 1, 3, 4);

        double inputs[] = {0.1, 0.5, 0.2, 0.9};
        double expectedOutputs[] = {0, 1, 0, 0};
        Neuron outputs[];

        outputs = network.evaluate(inputs);
        System.out.println(Arrays.toString(outputs));

        for (int i = 0; i < 10000; i++) {
            network.train(inputs, expectedOutputs, 1);
        }

        outputs = network.evaluate(inputs);
        System.out.println(Arrays.toString(outputs));
    }
}
