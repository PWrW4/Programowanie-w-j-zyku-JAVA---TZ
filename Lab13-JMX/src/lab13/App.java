package lab13;

import javax.management.*;
import javax.swing.*;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.Objects;

public class App extends JFrame {
    private JTextPane addField;
    private JPanel mainPanel;
    private JTextField timerText;
    private JLabel timerLabel;

    private ObjectName objectName = null;
    private MBeanServer server = null;
    private Advertisement advert = null;

    public App(String title) {
        super(title);
        Objects.requireNonNull(addField).setContentType("text/html");

        try {
            objectName = new ObjectName("lab13.Advertisement:type=basic,name=Advertisement");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        server = ManagementFactory.getPlatformMBeanServer();
        advert = new Advertisement();

        try {
            server.registerMBean(advert, objectName);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }

        setResizable(false);
        addField.setMaximumSize(new Dimension(550,400));
        setMinimumSize(new Dimension(600,600));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        App service = new App("Java TZ JMX");
    }
}
