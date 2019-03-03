package lab01;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExitButton();
            }
        });

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

        try {
            ICentrala nCentrala = (ICentrala) UnicastRemoteObject.exportObject(dialog, 1997);
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.bind("Centrala",nCentrala);
        } catch (AlreadyBoundException | IOException e) {
            e.printStackTrace();
        }

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public int zarejestrujBramke(Object bramka) throws RemoteException {
        return 1;
    }

    @Override
    public boolean wyrejestrujBramke(int nrBramki) throws RemoteException {
        return true;
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
