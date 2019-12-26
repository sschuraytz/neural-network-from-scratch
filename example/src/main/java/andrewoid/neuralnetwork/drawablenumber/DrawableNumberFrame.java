package andrewoid.neuralnetwork.drawablenumber;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class DrawableNumberFrame extends JFrame {

    public DrawableNumberFrame() {
        setTitle("Draw a Number");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        JButton clearButton = new JButton("Clear");
        root.add(clearButton, BorderLayout.SOUTH);
        root.setBackground(Color.GRAY);

        JPanel drawablePanel = new JPanel();
        JPanel outputPanel = new JPanel();
        JLabel drawableLabel = new JLabel("Draw Your Number Here");
        drawableLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        JLabel outputLabel = new JLabel("Here is Your Number");
        outputLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
        drawablePanel.add(drawableLabel, BorderLayout.EAST);
        outputPanel.add(outputLabel, BorderLayout.CENTER);
        drawablePanel.setBackground(Color.PINK);
        outputPanel.setBackground(Color.YELLOW);

        root.add(drawablePanel, BorderLayout.WEST);
        root.add(outputPanel, BorderLayout.EAST);

        DrawingComponent drawingComponent = new DrawingComponent();
//        drawablePanel.add(drawingComponent);
        root.add(drawingComponent);

        clearButton.addActionListener(e -> drawingComponent.clearImage());

        setContentPane(root);
        root.setVisible(true);
    }
}
