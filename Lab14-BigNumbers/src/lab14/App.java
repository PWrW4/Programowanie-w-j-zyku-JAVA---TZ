package lab14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        obliczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcAction();
            }
        });
    }

    private void calcAction(){
        Cubic cub = new Cubic();

        double a,b,c,d;

        try{
            a = Double.parseDouble(textA.getText());
            b = Double.parseDouble(textB.getText());
            c = Double.parseDouble(textC.getText());
            d = Double.parseDouble(textD.getText());
        }catch (NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Wrong input numbers format");
            return;
        }

        cub.solve(a,b,c,d);

        output.setText(cub.getResultString());
    }

    public static void main(String[] args) {
        App service = new App("Java Big Numbers");
    }
}
