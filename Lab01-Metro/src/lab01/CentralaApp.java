package lab01;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CentralaApp extends JDialog implements ICentrala {
    private JPanel contentPane;
    private JButton exitButton;
    private JList centralaList;
    private JButton zamknijButton;

    private Map<Integer,IBramka> mapBramek = new HashMap<Integer,IBramka>();
    private int licznik = 0;
    private IMonitor nMonitor = null;

    public CentralaApp() {
        try {
            ICentrala nCentrala = (ICentrala) UnicastRemoteObject.exportObject(this, PortFactory.getPort());
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.bind("Centrala",nCentrala);
        } catch (AlreadyBoundException | IOException e) {
            e.printStackTrace();
        }


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

        zamknijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onZamknijButton();
            }
        });

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(exitButton);

        updateList();
    }

    private void onZamknijButton(){
        try {
            int id = Integer.parseInt(centralaList.getSelectedValue().toString());
            mapBramek.get(id).zamknijBramke();
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
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
    public int zarejestrujBramke(IBramka bramka) throws RemoteException {
        licznik++;
        mapBramek.put(licznik,bramka);
        updateMonitor();
        updateList();
        return licznik;
    }

    @Override
    public boolean wyrejestrujBramke(int nrBramki) throws RemoteException {
        mapBramek.remove(nrBramki);
        updateMonitor();
        updateList();
        return true;
    }

    @Override
    public Map<Integer, IBramka> getZarejestrowaneBramki() throws RemoteException {
        return mapBramek;
    }

    @Override
    public void zarejestrujMonitor(IMonitor o) throws RemoteException {
        nMonitor = o;
    }

    @Override
    public void wyrejestrujMonitor() throws RemoteException {
        nMonitor = null;
    }

    private void updateMonitor() throws RemoteException {
        if (nMonitor!=null){
            nMonitor.koniecznaAktualizacja();
        }
    }

    private void updateList(){
        ArrayList<String> arrayIdBramek = new ArrayList<String>();

        arrayIdBramek.clear();

        for(Map.Entry<Integer,IBramka>  entry : mapBramek.entrySet()) {
            Integer key = entry.getKey();
            IBramka value = entry.getValue();


            arrayIdBramek.add(key.toString());
        }

        centralaList.setListData(new Vector<>(arrayIdBramek));
    }

}
