package lab08.centrala;

import lab08.gazetomat.Gazetomat;
import lab08.gazetomat.Paper;

import javax.swing.*;
import javax.xml.ws.Endpoint;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class CentralaApp extends JFrame implements Runnable {

    private JPanel MainPanel;
    private JList<String> mainList;

    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    private CentralaImpl centralaImpl;

    private ArrayList<String> frontArray = new ArrayList<String>();

    public CentralaApp() {
        super("Centrala App");
        centralaImpl = new CentralaImpl();
        Endpoint.publish("http://localhost:8080/WS/Centrala", centralaImpl);

        updateList();
        mainList.setModel(listModel);

        Thread thread = new Thread(this);
        thread.start();

        setMinimumSize(new Dimension(600,400));
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void updateList(){
        listModel.clear();
        frontArray.clear();
        for (Gazetomat gaz : centralaImpl.gazetomats){
            StringBuilder sb = new StringBuilder();
            sb.append(gaz.getId()).append(": ");
            for (Paper p : gaz.getItems()){
                sb.append(p.getName()).append(" : ").append(p.getCount());
            }
            frontArray.add(sb.toString());
        }
        for (int i=0;i<frontArray.size();i++){
            listModel.addElement(frontArray.get(i));
        }
        mainList.setModel(listModel);
    }


    public static void main(String[] args) {
        CentralaApp service = new CentralaApp();
        System.out.println("service up");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            for (Gazetomat g : centralaImpl.gazetomats){
                centralaImpl.updateRequest(g.getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
