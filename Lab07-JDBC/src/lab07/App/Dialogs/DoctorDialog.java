package lab07.App.Dialogs;

import lab07.DBService.DBController;
import lab07.DBService.Helpers.Emums.DoctorType;

import javax.swing.*;
import java.awt.event.*;

public class DoctorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textName;
    private JTextField textSurname;
    private JLabel labelName;
    private JLabel labelSurname;
    private JLabel labelType;
    private JComboBox<DoctorType> comboBoxTypes;

    int id=-1;
    DBController db;

    public DoctorDialog(int idExitsing, DBController db) {
        comboBoxTypes.setModel(new DefaultComboBoxModel<>(DoctorType.values()));
        this.db = db;
        if (idExitsing>0){
            id = idExitsing;
            textName.setText(db.getDoctorID(id).getName());
            textSurname.setText(db.getDoctorID(id).getSurname());
            String tmp_type = db.getDoctorID(id).getType().toString();

            for (int i=0;i<comboBoxTypes.getModel().getSize();i++) {
                if (tmp_type.hashCode() == comboBoxTypes.getModel().getElementAt(i).toString().hashCode()){
                    comboBoxTypes.getModel().setSelectedItem(comboBoxTypes.getModel().getElementAt(i));
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
        if (textName.getText().equals("") || textSurname.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Fields Empty");
            return;
        }

        if (id!=-1){
            db.updateDoctor(id,textName.getText(),textSurname.getText(),(DoctorType) comboBoxTypes.getModel().getSelectedItem());
        }else{
            db.addDoctor(textName.getText(),textSurname.getText(),(DoctorType) comboBoxTypes.getModel().getSelectedItem());
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
