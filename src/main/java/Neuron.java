public class Neuron {

    private static final double BIAS_UPPER = 0.7;
    private static final double BIAS_LOWER = 0.3;
    private static final double WEIGHT_UPPER = 0.5;
    private static final double WEIGHT_LOWER = 0.1;
    private Neuron previousLayer[];
    private double weights[];
    private double bias;
    private double value;
    private double derivative;
    private double error;
    /**
     * Construct a Neuron connected to the previous layer of Neurons
     *
     * @param previousLayer
     */
    public Neuron(Neuron[] previousLayer) {
        this.previousLayer = previousLayer;

        // if previousLayer is null then this is the first layer of the network.
        if (previousLayer != null) {
            weights = new double[previousLayer.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = randomInBounds(WEIGHT_LOWER, WEIGHT_UPPER);
            }
        }

        bias = randomInBounds(BIAS_LOWER, BIAS_UPPER);
    }

    /**
     * @param value
     * @return the sigmoid of the specified value
     */
    private static double sigmoid(double value) {
        return 1.0 / (1 + Math.exp(-value));
    }

    /**
     * @param lower
     * @param upper
     * @return a double between lower and upper
     */
    private static double randomInBounds(double lower, double upper) {
        return Math.random() * (upper - lower) + lower;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @param index
     * @return the weights of the connection from the previous layer
     */
    public double getWeight(int index) {
        return weights[index];
    }

    public double getError() {
        return error;
    }

    /**
     * Computer the value of this Neuron given the previousLayer and weights.
     */
    public void computeValue() {
        double sum = bias;
        for (int i = 0; i < weights.length; i++) {
            Neuron n = previousLayer[i];
            double weight = weights[i];
            sum += n.value * weight;
        }
        value = sigmoid(sum);
        derivative = value * (1.0 - value);
    }

    /**
     * Compute the error if the Neuron was in the outer most layer.
     *
     * @param expectedValue
     */
    public void computeOuterError(double expectedValue) {
        error = (value - expectedValue) * derivative;
    }

    /**
     * Compute the error if the Neuron was in an inner layer of the network.
     *
     * @param sum
     */
    public void computeInnerError(double sum) {
        error = sum * derivative;
    }

    /**
     * Updates the weights given the previousLayer and the learningRate
     *
     * @param learningRate
     */
    public void updateWeights(double learningRate) {
        double delta = -learningRate * error;
        bias += delta;

        for (int i = 0; i < weights.length; i++) {
            Neuron n = previousLayer[i];
            weights[i] += delta * n.value;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
