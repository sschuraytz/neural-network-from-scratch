package andrewoid.neuralnetwork.digit_ui;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.Neuron;

public class DigitInteraction {

    public static void main(String[] args) {
        Network network = new NetworkFactory().loadFromJSON("network.json");
        new DigitFrame(network).setVisible(true);
    }
}
