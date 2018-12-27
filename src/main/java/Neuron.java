public class Neuron {

    public static final double BIAS_UPPER = 0.7;
    public static final double BIAS_LOWER = 0.3;
    public static final double WEIGHT_UPPER = 0.5;
    public static final double WEIGHT_LOWER = 0.1;
    private double bias;
    private double weights[];

    private double value;
    private double derivative;
    private double error;

    private Neuron previousLayer[];

    public Neuron(Neuron[] previousLayer) {
        this.previousLayer = previousLayer;
        if (previousLayer != null) {
            weights = new double[previousLayer.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = randomInBounds(WEIGHT_LOWER, WEIGHT_UPPER);
            }
        }
        bias = randomInBounds(BIAS_LOWER, BIAS_UPPER);
    }

    private static double sigmoid(double sum) {
        return 1.0 / (1 + Math.exp(-sum));
    }

    private static double randomInBounds(double lower, double upper) {
        return Math.random() * (upper - lower) + lower;
    }

    public double getValue() {
        return value;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight(int index) {
        return weights[index];
    }

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

    public void computeOuterError(double expectedValue) {
        error = (value - expectedValue) * derivative;
    }

    public void computeInnerError(double sum) {
        error = sum * derivative;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public double getError() {
        return error;
    }

    public void updateWeights(double learningRate) {
        if (weights == null) {
            // first layer
            return;
        }

        double delta = -learningRate * error;
        bias += delta;

        for (int i = 0; i < weights.length; i++) {
            Neuron n = previousLayer[i];
            weights[i] += delta * n.value;
        }
    }

}
