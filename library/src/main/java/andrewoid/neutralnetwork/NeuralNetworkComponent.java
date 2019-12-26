package andrewoid.neutralnetwork;

import javax.swing.*;
import java.awt.*;


public class NeuralNetworkComponent extends JComponent {
    int start = 25;
    int size = 10;
    int space = 50;
    Network network;
    Color[] colors = {Color.RED,Color.ORANGE,Color.YELLOW, Color.GREEN, Color.BLUE,Color.CYAN,Color.MAGENTA, Color.PINK};

    public NeuralNetworkComponent(Network network) {
        this.network = network;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Color neuronColor = Color.BLACK;
        int neuronValue;
        int pathWeight;
        Color pathColor = Color.BLACK;
        for (int i = 0;i <network.getLayers().length; i ++){
            for ( int j = 0; j<network.getLayers()[i].length; j ++ )

            {
             neuronValue = (int) network.getLayers()[i][j].getValue();
             neuronColor = colors[neuronValue] ;
             drawNeuron(i,j, neuronColor, g);
//
//          if (i<network.layers.length-1){
//             for (int k = 0; k <network.layers[i][j].weights.length; k++ )
//             {
//                pathWeight = (int) network.layers[i][j].getWeight(k);
//                pathColor = colors[pathWeight];
//                drawConection(i,j,k, pathColor, g);
//             }}

            }
        }



    }
    private void drawNeuron(int row, int column, Color color, Graphics g){
        g.setColor(color);
        g.drawOval(start+(row*space),start+(column*space),size,size);


    }
    private void drawConection(int row, int column, int number, Color color, Graphics g){
        g.drawLine(start+(row*space)+size,start+(column*space), start+(number*space), start+(column+1*space) );
    }

}
