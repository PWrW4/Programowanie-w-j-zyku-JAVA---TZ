package lab14;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextField textB;
    private JTextField textA;
    private JTextField textC;
    private JTextField textD;
    private JTextArea output;
    private JButton obliczButton;
    private JLabel wynikLabel;


    public App(String title) throws HeadlessException {
        super(title);

        setResizable(false);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        App service = new App("Java Big Numbers");
    }
}
