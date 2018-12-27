import java.util.ArrayList;
import java.util.List;

/**
 * Neural Network object
 */
public class Network {

    private final List<Neuron[]> layers;

    /**
     * Construct a neural network with layers and different number of Neurons per layer
     *
     * @param sizes
     */
    public Network(int... sizes) {
        layers = new ArrayList<>();
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

    /**
     * @param inputs
     * @return the output layer after evaluating the given inputs
     */
    public Neuron[] evaluate(double... inputs) {
        Neuron firstLayer[] = layers.get(0);
        for (int i = 0; i < inputs.length; i++) {
            firstLayer[i].setValue(inputs[i]);
        }

        for (int i = 1; i < layers.size(); i++) {
            for (Neuron n : layers.get(i)) {
                n.computeValue();
            }
        }

        return layers.get(layers.size() - 1);
    }

    /**
     * Train the network on input data, expected output and a learning rate.
     *
     * @param inputs
     * @param expectedOutputs
     * @param learningRate
     */
    public void train(double inputs[], double expectedOutputs[], double learningRate) {
        evaluate(inputs);
        computerErrors(expectedOutputs);
        updateWeights(learningRate);
    }

    private void computerErrors(double expectedOutputs[]) {
        Neuron outputs[] = layers.get(layers.size() - 1);
        // compute the errors of the outer layer
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].computeOuterError(expectedOutputs[i]);
        }

        // go backwards through our inner layers
        for (int i = layers.size() - 2; i > 0; i--) {
            Neuron layer[] = layers.get(i);
            for (int j = 0; j < layer.length; j++) {
                Neuron n = layer[j];
                double sum = 0;
                for (Neuron neuron : layers.get(i + 1)) {
                    sum += (neuron.getWeight(j) * neuron.getError());
                }
                n.computeInnerError(sum);
            }
        }
    }

    private void updateWeights(double learningRate) {
        for (int i = 1; i < layers.size(); i++) {
            for (Neuron n : layers.get(i)) {
                n.updateWeights(learningRate);
            }
        }
    }

}
