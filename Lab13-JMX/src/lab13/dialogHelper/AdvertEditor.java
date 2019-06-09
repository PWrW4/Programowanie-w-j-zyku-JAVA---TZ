package lab13.dialogHelper;

import lab13.Advertisement;
import lab13.BannerController;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

public class AdvertEditor extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField inputId;
    private JTextField inputText;
    private JTextField inputTime;
    private JLabel idLabel;
    private JLabel advertText;
    private JLabel advertTime;

    HashMap<Integer, Advertisement> adverts;

    public AdvertEditor(HashMap<Integer, Advertisement> adv) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        adverts = adv;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onOK() {
        try{
            int id = Integer.parseInt(inputId.getText());
            int time = Integer.parseInt(inputTime.getText());
            String text = inputText.getText();

            adverts.get(id).setAddvString(text);
            adverts.get(id).setAddvTime(time);

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Błędne dane");
            return;
        }

        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
