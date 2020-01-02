package andrewoid.neuralnetwork.digit_ui;

import andrewoid.neutralnetwork.Network;
import andrewoid.neutralnetwork.Neuron;
import javax.swing.*;

public class EvaluateButton extends JButton {

    private Network network;
    private ResultScreen resultScreen;

    public EvaluateButton(Network network, ResultScreen resultScreen) {
        this.network = network;
        this.resultScreen = resultScreen;
        setText("Evaluate");
    }

    public void evaluate(double[] input) {
        // test the network on the input sets, printing out everything that evaluates to greater than 10%
            StringBuilder result = new StringBuilder();
            Neuron[] outputs = network.evaluate(input);
            for (int i = 0; i < outputs.length; i++) {
                double value = outputs[i].getValue();
                if (value > 0.10) {
                    result.append(i);
                    result.append(" ");
                }
            }
            resultScreen.setText(result.toString());
    }
}
