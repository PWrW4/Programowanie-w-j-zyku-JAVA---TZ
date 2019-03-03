package lab01;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CentralaApp extends JDialog implements ICentrala {
    private JPanel contentPane;
    private JButton exitButton;
    private JTable centralaTable;

    public CentralaApp() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(exitButton);



        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExitButton();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExitButton();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExitButton();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onExitButton() {
        System.exit(0);
        dispose();
    }

    public static void main(String[] args) {
        CentralaApp dialog = new CentralaApp();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public int zarejestrujBramke(Object bramka) throws RemoteException {
        return 0;
    }

    @Override
    public boolean wyrejestrujBramke(int nrBramki) throws RemoteException {
        return false;
    }

    @Override
    public ArrayList<Object> getZarejestrowaneBramki() throws RemoteException {
        return null;
    }

    @Override
    public void zarejestrujMonitor(Object o) throws RemoteException {

    }

    @Override
    public void wyrejestrujMonitor() throws RemoteException {

    }

    @Override
    public Object getMonitor() throws RemoteException {
        return null;
    }
}
