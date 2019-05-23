package lab08.gazetomat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GazetomatApp extends JFrame {


    private JList<String> listaGazet;
    private JPanel MainPanel;
    private JButton buyPaper;
    private JButton register;
    private JButton unregister;

    private ArrayList<String> frontArray = new ArrayList<String>();
    private DefaultListModel<String> listModel = new DefaultListModel<String>();
    private GazetomatImpl automat;


    public GazetomatApp() {
        super("Gazetomat");
        setMinimumSize(new Dimension(600, 400));
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        buyPaper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyPaperAction();
            }
        });
        unregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unregisterAction();
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionRegister();
            }
        });
        automat = new GazetomatImpl();
        updateList();
    }

    private void buyPaperAction() {
        if (!listaGazet.isSelectionEmpty()) {
            StringBuilder sb;
            for (Paper p : automat.getItems()) {
                sb = new StringBuilder();
                if (sb.append(p.getName()).append(" : ").append(p.getCount()).toString().equals(listaGazet.getSelectedValue())) {
                    p.setCount(p.getCount()-1);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nothing selected");
        }
        updateList();
    }

    private void unregisterAction() {
        automat.unregister();
    }

    private void actionRegister() {
        automat.register();
    }

    private void updateList() {
        listModel.clear();
        frontArray.clear();

        StringBuilder sb;
        for (Paper p : automat.getItems()) {
            sb= new StringBuilder();
            sb.append(p.getName()).append(" : ").append(p.getCount());
            frontArray.add(sb.toString());
        }


        for (int i = 0; i < frontArray.size(); i++) {
            listModel.addElement(frontArray.get(i));
        }
        listaGazet.setModel(listModel);
    }

    public static void main(String[] args) {
        GazetomatApp automat = new GazetomatApp();
    }


}
