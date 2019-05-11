package lab07.App.Dialogs;

import lab07.DBService.DBController;
import lab07.DBService.Helpers.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textName;
    private JLabel labelName;
    private JTextField textSurname;
    private JLabel labelSurname;

    int id=-1;
    DBController db;


    public PatientDialog(int idExitsing, DBController db) {
        this.db = db;
        if (idExitsing>0){
            id = idExitsing;
            textName.setText(db.getPatientID(id).getName());
            textSurname.setText(db.getPatientID(id).getSurname());
        }else{
            id = -1;
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(200,300));

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
        if (textName.getText().equals("") || textSurname.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Fields Empty");
            return;
        }
        if (id!=-1){
            db.updatePatient(id,textName.getText(),textSurname.getText());
        }else{
            db.addPatient(textName.getText(),textSurname.getText());
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
