package lab01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;

public class BramkaApp extends JDialog implements IBramka{
    private JPanel contentPane;
    private JButton buttonStart;
    private JButton buttonStop;
    private JButton buttonWejscie;
    private JButton buttonWyjscie;

    public BramkaApp() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonStart);

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonWejscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonWyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
    public int[] getStatystyka(Date pocz, Date kon) throws RemoteException {
        return new int[0];
    }

    @Override
    public boolean zamknijBramke() throws RemoteException {
        return false;
    }

    @Override
    public int getNumer() throws RemoteException {
        return 0;
    }
}
