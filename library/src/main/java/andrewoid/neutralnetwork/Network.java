package andrewoid.neutralnetwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Multi layer Neural andrewoid.neutralnetwork.Network object that encapsulates evaluation and training.
 */
public class Network {

    public Neuron[][] getLayers() {
        return layers;
    }

    private final Neuron layers[][];

    public Neuron[][] getLayers()
    {
        return layers;
    }

    /**
     * Construct a neural network with layers and different number of Neurons per layer
     *
     * @param sizes
     */
    public Network(int... sizes) {
        layers = new Neuron[sizes.length][];

        // create the individual layers
        for (int i = 0; i < sizes.length; i++) {
            layers[i] = new Neuron[sizes[i]];
        }

        // create each andrewoid.neutralnetwork.Neuron with links to the previous and next layers
        for (int i = 0; i < sizes.length; i++) {
            Neuron layer[] = layers[i];
            for (int j = 0; j < layer.length; j++) {
                Neuron[] previousLayer = (i == 0) ? null : layers[i - 1];
                Neuron[] nextLayer = (i == sizes.length - 1) ? null : layers[i + 1];
                layer[j] = new Neuron(j, previousLayer, nextLayer);
            }
            layers[i] = layer;
        }
    }

    public void saveToJSON(String fileName)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try
        {
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(this, writer);
            writer.flush();
            writer.close();
        }
        catch (IOException exc)
        {
            exc.printStackTrace();
        }
    }

    public Network(Neuron[][] layers)
    {
        this.layers = layers;
    }

    public static String toString(Neuron layer[], double threshold) {
        return Arrays.stream(layer)
                .map(n -> n.getValue() > threshold ? String.valueOf(n.getValue()) : "_")
                .collect(Collectors.joining(", "));
    }

    /**
     * @param inputs
     * @return the output layer after evaluating the given inputs
     */
    public Neuron[] evaluate(double... inputs) {
        Neuron firstLayer[] = layers[0];
        for (int i = 0; i < inputs.length; i++) {
            firstLayer[i].setValue(inputs[i]);
        }

        for (int i = 1; i < layers.length; i++) {
            for (Neuron n : layers[i]) {
                n.computeValue();
            }
        }

        // return the last layer
        return layers[layers.length - 1];
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
        Neuron outputs[] = layers[layers.length - 1];
        // compute the errors of the outer layer
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].computeOuterError(expectedOutputs[i]);
        }

        // go backwards through our inner layers
        for (int i = layers.length - 2; i > 0; i--) {
            Neuron layer[] = layers[i];
            for (Neuron n : layer) {
                n.computeInnerError();
            }
        }
    }

    private void updateWeights(double learningRate) {
        for (int i = 1; i < layers.length; i++) {
            for (Neuron n : layers[i]) {
                n.updateWeights(learningRate);
            }
        }
    }

     void connectNeurons()
    {
        for (int ix = 0; ix < layers.length; ++ix)
        {
            for (int ij = 0; ij < layers[ix].length; ++ij)
            {
                if (ix > 0)
                {
                    layers[ix][ij].setPreviousLayer(layers[ix - 1]);
                }
                if (ix < layers.length - 1)
                {
                    layers[ix][ij].setNextLayer(layers[ix + 1]);
                }
            }
        }
    }
}
