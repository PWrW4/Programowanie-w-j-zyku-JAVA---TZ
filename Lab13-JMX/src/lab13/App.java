package lab13;

import lab13.dialogHelper.AdvertEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class App extends JFrame {
    private JTextPane adField;
    private JPanel mainPanel;
    private JLabel timerText;
    private JLabel timerLabel;
    private JButton editButton;
    private JLabel idText;
    private JLabel idLabel;

    private BannerController bannerController;

    public App(String title) {
        super(title);
        Objects.requireNonNull(adField).setContentType("text/html");

        bannerController = new BannerController(adField,timerText,idText);
        bannerController.addAdvert(1000,"<h1>Reklama-------1</h1>");
        bannerController.addAdvert(3000,"<h1>Reklama-------2</h1>");
        bannerController.addAdvert(5000,"<h1>Reklama-------3</h1>");
        bannerController.addAdvert(10000,"<h1>Reklama-------4</h1>");
        bannerController.Start();

        setResizable(false);
        adField.setMaximumSize(new Dimension(550,400));
        setMinimumSize(new Dimension(600,600));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esitAcciton();
            }
        });
    }

    private void esitAcciton() {
        new AdvertEditor(bannerController.getHmap());
    }

    public static void main(String[] args) {
        App service = new App("Java TZ JMX");
    }


}
