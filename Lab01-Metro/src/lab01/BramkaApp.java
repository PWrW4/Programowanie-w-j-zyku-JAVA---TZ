package lab01;

import javax.net.SocketFactory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class BramkaApp extends JDialog implements IBramka{
    private JPanel contentPane;
    private JButton buttonStart;
    private JButton buttonStop;
    private JButton buttonWejscie;
    private JButton buttonWyjscie;
    private JLabel wejsciaLabel;
    private JLabel wyjsciaLabel;

    private Bramka bramka;
    private ICentrala nCentrala;
    private IBramka nBramka;


    private BramkaApp() {
        bramka = new Bramka(-1,0,0,false);

        try {
            nBramka = (IBramka) UnicastRemoteObject.exportObject(this, PortFactory.getPort());
            Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);

            nCentrala = (ICentrala) registry.lookup("Centrala");

        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"No Centrala running");
            System.exit(1);
        }

        updateButtonsStatus();

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonStart);

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nCentrala.wyrejestrujBramke(bramka.getNr());
                    bramka.setNr(-1);
                    updateButtonsStatus();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
        buttonWejscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bramka.setWekscia(bramka.getWekscia() + 1);
                wejsciaLabel.setText("Wejscia: " + bramka.getWekscia());
            }
        });
        buttonWyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bramka.setWyjscia(bramka.getWyjscia()+1);
                wyjsciaLabel.setText("Wyjscia: " + bramka.getWyjscia());
            }
        });
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bramka.setNr(nCentrala.zarejestrujBramke(nBramka));
                    updateButtonsStatus();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        BramkaApp dialog = new BramkaApp();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


    @Override
    public int[] getStatystyka() throws RemoteException {
        return new int[]{
                bramka.getWekscia(),
                bramka.getWyjscia(),
        };
    }

    @Override
    public boolean zamknijBramke() throws RemoteException {
        bramka.setRunning(false);
        nCentrala.wyrejestrujBramke(bramka.getNr());
        bramka.setNr(-1);
        updateButtonsStatus();
        return true;
    }

    private void updateButtonsStatus(){
        if (bramka.isRunning()){
            buttonWyjscie.setEnabled(true);
            buttonWejscie.setEnabled(true);
            buttonStart.setEnabled(false);
            buttonStop.setEnabled(true);
        }else{
            buttonWyjscie.setEnabled(false);
            buttonWejscie.setEnabled(false);
            buttonStart.setEnabled(true);
            buttonStop.setEnabled(false);
        }
        bramka.setRunning(!bramka.isRunning());
    }
}
