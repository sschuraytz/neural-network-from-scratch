import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Network {

    private List<Neuron[]> layers;

    public Network(int ... sizes) {
        layers = new ArrayList<>();
        int layerNum = 0;
        Neuron[] previousLayer = null;
        for (int size : sizes) {
            Neuron layer[] = new Neuron[size];
            for (int i = 0; i < size; i++) {
                layer[i] = new Neuron(previousLayer);
            }
            layers.add(layer);
            previousLayer = layer;
        }
    }

    public Neuron[] calculate(double ... inputs) {
        Neuron firstLayer[] = layers.get(0);
        for (int i = 0; i < inputs.length; i++) {
            firstLayer[i].setValue(inputs[i]);
        }

        for (int i = 1; i < layers.size(); i++) {
            for ( Neuron n : layers.get(i)) {
                n.computeValue();
            }
        }

        return layers.get(layers.size()-1);
    }

    public void train(double inputs[], double expectedOutputs[], double learningRate) {
        calculate(inputs);
        computerErrors(inputs, expectedOutputs);
        updateWeights(learningRate);
    }

    public void computerErrors(double inputs[], double expectedOutputs[]) {
        Neuron outputs[] = layers.get(layers.size()-1);
        // compute the errors of the outer layer
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].computeOuterError(expectedOutputs[i]);
        }

        // go backwards through our inner layers
        for (int i = layers.size()-2; i > 0; i--) {
            Neuron layer[] = layers.get(i);
            for (int j = 0; j < layer.length; j++) {
                Neuron n = layer[j];
                double sum = 0;
                for (Neuron neuron : layers.get(i+1)) {
                    sum += (neuron.getWeight(j) * neuron.getError());
                }
                n.computeInnerError(sum);
            }
        }
    }

    private void updateWeights(double learningRate) {
        for (Neuron layer[]: layers) {
            for (Neuron n : layer) {
                n.updateWeights(learningRate);
            }
        }
    }

    public static void main(String args[]) {
        Network network = new Network(4, 1, 3, 4);

        double inputs[] = {0.1, 0.5, 0.2, 0.9};
        double expectedOutputs[] = {0, 1, 0, 0};

        for (int i=0;i<10000; i++) {
            network.train(inputs, expectedOutputs, 1);
        }

        Neuron outputs[] = network.calculate(inputs);
        System.out.println(Arrays.toString(outputs));
    }

}
