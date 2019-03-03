package lab01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class MonitorApp extends JDialog implements IMonitor, Runnable {
    private JPanel contentPane;
    private JButton buttonExit;
    private JList<String> bramkiList;
    private ListModel<String> li = new DefaultListModel<String>();


    private ArrayList<String> arrayBramek = new ArrayList<String>();
    private ICentrala nCentrala;

    private MonitorApp() {

        try {
            IMonitor nMonitor = (IMonitor) UnicastRemoteObject.exportObject(this, 2000);
            Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);

            nCentrala = (ICentrala) registry.lookup("Centrala");
            nCentrala.zarejestrujMonitor(this);

        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        }


        try {
            updateBramkiList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonExit);
        bramkiList.addComponentListener(new ComponentAdapter() {
        });

        (new Thread(this)).start();
    }

    public static void main(String[] args) {
        MonitorApp dialog = new MonitorApp();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public void koniecznaAktualizacja() throws RemoteException {
        updateBramkiList();
    }

    private void updateBramkiList() throws RemoteException {

        arrayBramek.clear();

        for(Map.Entry<Integer,IBramka>  entry : nCentrala.getZarejestrowaneBramki().entrySet()) {
            Integer key = entry.getKey();
            IBramka value = entry.getValue();

            int[] tmpData = value.getStatystyka();

            arrayBramek.add("id: " + key.toString() + " ; Wejsc: " + tmpData[0] + " ; Wyjsc: " + tmpData[1]);
        }

        bramkiList.setListData(new Vector<>(arrayBramek));


    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5000);
                updateBramkiList();
            } catch (InterruptedException | RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
