package andrewoid.neuralnetwork.digit_ui;

import andrewoid.neuralnetwork.Network;
import andrewoid.neuralnetwork.NetworkFactory;

import java.io.FileNotFoundException;

public class DigitInteraction {

    public static void main(String[] args) throws FileNotFoundException {
        Network network = new NetworkFactory().loadFromJSON("network.json");
        new DigitFrame(network).setVisible(true);
    }
}
