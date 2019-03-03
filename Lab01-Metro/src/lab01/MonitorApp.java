package lab01;

import javax.swing.*;
import java.rmi.RemoteException;

public class MonitorApp extends JDialog implements IMonitor {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonExit;
    private JTable monitorTable;

    public MonitorApp() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        MonitorApp dialog = new MonitorApp();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public void koniecznaAktualizacja() throws RemoteException {

    }
}
