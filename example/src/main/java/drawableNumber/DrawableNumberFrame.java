package drawableNumber;
import javax.swing.*;
import java.awt.BorderLayout;

public class DrawableNumberFrame extends JFrame {

    private JButton submitButton = new JButton("Find my Number");
    private JButton clearButton = new JButton("Clear");
    private DrawingComponent drawingComponent = new DrawingComponent();
    private JPanel drawingPanel = new JPanel();
   // private JLabel drawingLabel = new JLabel("ok");

    public DrawableNumberFrame() {
        setTitle("Draw A Number");
        setSize(300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        JTextField outputText = new JTextField("You Drew: ");
        outputText.setEditable(false);
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(outputText);
        root.add(buttonPanel, BorderLayout.SOUTH);

        //potential issue: drawingPanel is initially empty b/c it has nothing.
//        drawingLabel.setIcon(new ImageIcon(drawingComponent.getImage()));
//        root.add(drawingLabel, BorderLayout.NORTH);
        drawingPanel.add(drawingComponent);
        drawingPanel.revalidate();
        drawingPanel.repaint();
       // root.add(drawingPanel);
       // root.add(drawingComponent);

        setContentPane(root);
        root.setVisible(true);

        clearButton.addActionListener(e -> drawingComponent.clearImage());
    }
}