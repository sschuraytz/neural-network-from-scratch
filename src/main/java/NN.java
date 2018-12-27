 /**
 *
 * @author Deus Jeraldy
 * @Email: deusjeraldy@gmail.com
 * BSD License
 */

// np.java -> https://gist.github.com/Jeraldy/7d4262db0536d27906b1e397662512bc

import java.util.Arrays;

public class NN {

    double[][] X = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
    double[][] Y = {{0}, {1}, {1}, {0}};

    int m = 4;
    int nodes = 400;

    double[][] W1 = np.random(nodes, 2);
    double[][] b1 = new double[nodes][m];

    double[][] W2 = np.random(1, nodes);
    double[][] b2 = new double[1][m];

    double[][] A1;
    double[][] predictions;
    double cost;

    public NN() {
        X = np.T(X);
        Y = np.T(Y);
    }

    public void advance(int iterations) {
        for (int i = 0; i < iterations; i++) {
            forwardPropagation();
            backwardPropagation();
            if (i % 400 == 0) {
                printPredictions();
            }
        }
    }

    private void backwardPropagation() {
        // Back Prop
        //LAYER 2
        double[][] dZ2 = np.subtract(predictions, Y);
        double[][] dW2 = np.divide(np.dot(dZ2, np.T(A1)), m);
        double[][] db2 = np.divide(dZ2, m);

        //LAYER 1
        double[][] dZ1 = np.multiply(np.dot(np.T(W2), dZ2), np.subtract(1.0, np.power(A1, 2)));
        double[][] dW1 = np.divide(np.dot(dZ1, np.T(X)), m);
        double[][] db1 = np.divide(dZ1, m);

        // G.D
        W1 = np.subtract(W1, np.multiply(0.01, dW1));
        b1 = np.subtract(b1, np.multiply(0.01, db1));

        W2 = np.subtract(W2, np.multiply(0.01, dW2));
        b2 = np.subtract(b2, np.multiply(0.01, db2));
    }

    private void printPredictions() {
        System.out.println("==============");
        System.out.println("Cost = " + cost);
        System.out.println("Predictions = " + Arrays.deepToString(predictions));
    }

    private void forwardPropagation() {
        // Foward Prop
        // LAYER 1
        double[][] Z1 = np.add(np.dot(W1, X), b1);
        A1 = np.sigmoid(Z1);

        //LAYER 2
        double[][] Z2 = np.add(np.dot(W2, A1), b2);
        predictions = np.sigmoid(Z2);

        cost = np.cross_entropy(m, Y, predictions);
        //costs.getData().add(new XYChart.Data(i, cost));
    }


    public static void main(String[] args) {
        NN network = new NN();

        network.advance(4000);
    }
}