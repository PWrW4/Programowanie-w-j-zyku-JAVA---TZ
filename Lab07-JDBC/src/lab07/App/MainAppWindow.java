package lab07.App;

import lab07.App.Dialogs.DoctorDialog;
import lab07.App.Dialogs.PatientDialog;
import lab07.DBService.DBController;
import lab07.DBService.Helpers.TableModels.DoctorTModel;
import lab07.DBService.Helpers.TableModels.PatientTModel;
import lab07.DBService.Helpers.TableModels.VisitTModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAppWindow extends JFrame {
    private JTabbedPane Tabs;
    private JPanel MainPanel;
    private JPanel Doctors;
    private JPanel Patinets;
    private JPanel Visits;
    private JTable doctorTable;
    private JTable visitTable;
    private JTable patientTable;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel buttonPanel;

    private DBController dbController = new DBController();

    public MainAppWindow(String title){
        super(title);

        doctorTable.setModel(new DoctorTModel(dbController.getDoctors()));
        patientTable.setModel(new PatientTModel(dbController.getPatients()));
        visitTable.setModel(new VisitTModel(dbController.getVisits()));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAction();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAction();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction();
            }
        });

        setMinimumSize(new Dimension(600,400));
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void deleteAction(){
        try {
            switch (Tabs.getTitleAt(Tabs.getSelectedIndex())) {
                case "Doctors":
                    dbController.deleteDoctor((int) doctorTable.getModel().getValueAt(doctorTable.getSelectedRow(), 0));
                    break;
                case "Patients":
                    dbController.deletePatient((int) patientTable.getModel().getValueAt(patientTable.getSelectedRow(), 0));
                    break;
                case "Visits":
                    dbController.deleteVisit((int) visitTable.getModel().getValueAt(visitTable.getSelectedRow(), 0));
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error in switch");
                    break;
            }
            doctorTable.setModel(new DoctorTModel(dbController.getDoctors()));
            patientTable.setModel(new PatientTModel(dbController.getPatients()));
            visitTable.setModel(new VisitTModel(dbController.getVisits()));
        }catch (ArrayIndexOutOfBoundsException e){
        JOptionPane.showMessageDialog(null,"No item selected");
        }
    }

    private void editAction(){
        try {
            switch (Tabs.getTitleAt(Tabs.getSelectedIndex())) {
                case "Doctors":
                    new DoctorDialog((int) doctorTable.getModel().getValueAt(doctorTable.getSelectedRow(), 0), dbController);
                    break;
                case "Patients":
                    new PatientDialog((int) patientTable.getModel().getValueAt(patientTable.getSelectedRow(), 0), dbController);
                    break;
                case "Visits":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error in switch");
                    break;
            }
            doctorTable.setModel(new DoctorTModel(dbController.getDoctors()));
            patientTable.setModel(new PatientTModel(dbController.getPatients()));
            visitTable.setModel(new VisitTModel(dbController.getVisits()));
        }catch (ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"No item selected");
        }
    }

    private void addAction(){
        try {
            switch (Tabs.getTitleAt(Tabs.getSelectedIndex())) {
                case "Doctors":
                    new DoctorDialog(-1, dbController);
                    break;
                case "Patients":
                    new PatientDialog(-1, dbController);
                    break;
                case "Visits":
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error in switch");
                    break;
            }
            doctorTable.setModel(new DoctorTModel(dbController.getDoctors()));
            patientTable.setModel(new PatientTModel(dbController.getPatients()));
            visitTable.setModel(new VisitTModel(dbController.getVisits()));
        }catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No item selected");
        }
    }

    public static void main(String[] args) {
        MainAppWindow app = new MainAppWindow("JDBC");
    }
}
