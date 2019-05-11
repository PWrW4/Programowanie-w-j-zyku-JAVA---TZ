package lab07.App.Dialogs;

import lab07.DBService.DBController;
import lab07.DBService.Helpers.Doctor;
import lab07.DBService.Helpers.Patient;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class VisitDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel labelRoom;
    private JLabel labelFinished;
    private JLabel labelTime;
    private JLabel labelDoctor;
    private JLabel labelPatient;
    private JComboBox<Object> patientCombo;
    private JComboBox<Object> doctorCombo;
    private JTextField timeText;
    private JTextField roomText;
    private JCheckBox finishedCheckBox;

    int id=-1;
    DBController db;

    public VisitDialog(int idExitsing, DBController db) {
        this.db = db;
        patientCombo.setModel(new DefaultComboBoxModel<>(db.getPatients().toArray()));
        doctorCombo.setModel(new DefaultComboBoxModel<>(db.getDoctors().toArray()));
        if (idExitsing>0){
            id = idExitsing;
            timeText.setText(db.getVisitID(id).getTime().toString());
            roomText.setText(Integer.toString(db.getVisitID(id).getRoom()));
            if (db.getVisitID(id).getFinished()==0){
                finishedCheckBox.setSelected(false);
            }else{
                finishedCheckBox.setSelected(true);
            }

            String tmp_type = db.getVisitID(id).getPatient().toString();

            for (int i=0;i<patientCombo.getModel().getSize();i++) {
                if (tmp_type.hashCode() == patientCombo.getModel().getElementAt(i).toString().hashCode()){
                    patientCombo.getModel().setSelectedItem(patientCombo.getModel().getElementAt(i));
                }
            }

            tmp_type = db.getVisitID(id).getDoctor().toString();

            for (int i=0;i<doctorCombo.getModel().getSize();i++) {
                if (tmp_type.hashCode() == doctorCombo.getModel().getElementAt(i).toString().hashCode()){
                    doctorCombo.getModel().setSelectedItem(doctorCombo.getModel().getElementAt(i));
                }
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

        if (finishedCheckBox.isSelected()){
            tmp_int = 1;
        }

        int doctor=1;
        int patient=1;

        Patient tmp_Patient = null;
        int int_Patient = -1;
        Doctor tmp_Doctor = null;
        int int_Doctor = -1;

        for (Patient p :
                db.getPatients()) {
            if (p.toString().hashCode() == patientCombo.getSelectedItem().toString().hashCode()){
                int_Patient = p.getId();
            }
        }

        for (Doctor d :
                db.getDoctors()) {
            if (d.toString().hashCode() == doctorCombo.getSelectedItem().toString().hashCode()){
                int_Doctor = d.getId();
            }
        }

        if (id!=-1){
            db.updateVisit(id,Integer.parseInt(roomText.getText()),tmp_int,new Date(),int_Doctor,int_Patient);
        }else{
            db.addVisit(Integer.parseInt(roomText.getText()),tmp_int,new Date(),int_Doctor,int_Patient);
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
