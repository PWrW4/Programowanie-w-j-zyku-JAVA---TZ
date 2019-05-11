package lab07.App.Dialogs;

import lab07.DBService.DBController;

import javax.swing.*;
import java.awt.event.*;

public class VisitDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelRoom;
    private JLabel labelFinished;
    private JLabel labelTime;
    private JLabel labelDoctor;
    private JLabel labelPatient;
    private JComboBox patientCheckBox;
    private JComboBox doctorCheckBox;
    private JTextField timeText;
    private JTextField roomText;
    private JCheckBox finishedCheckBox;

    int id=-1;
    DBController db;

    public VisitDialog(int idExitsing, DBController db) {
        this.db = db;
        if (idExitsing>0){
            id = idExitsing;
            timeText.setText(db.getVisitID(id).getTime().toString());
            roomText.setText(Integer.toString(db.getVisitID(id).getRoom()));
            if (db.getVisitID(id).getFinished()==0){
                finishedCheckBox.setSelected(false);
            }else{
                finishedCheckBox.setSelected(true);
            }
        }else{
            id = -1;
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        if (timeText.getText().equals("") || roomText.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Fields Empty");
            return;
        }

        int tmp_int=0;

        if ()

        if (id!=-1){
            db.updateVisit(id,textName.getText(),textSurname.getText());
        }else{
            db.addVisit(Integer.parseInt(roomText.getText()),);
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
