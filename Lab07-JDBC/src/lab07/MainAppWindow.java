package lab07;

import lab07.DBService.DBController;
import lab07.DBService.Helpers.TableModels.DoctorTModel;
import lab07.DBService.Helpers.TableModels.PatientTModel;
import lab07.DBService.Helpers.TableModels.VisitTModel;

import javax.swing.*;
import java.awt.*;

public class MainAppWindow extends JFrame {
    private JTabbedPane Tabs;
    private JPanel MainPanel;
    private JPanel Doctors;
    private JPanel Patinets;
    private JPanel Visits;
    private JTable doctorTable;
    private JTable visitTable;
    private JTable patientTable;

    private DBController dbController = new DBController();

    public MainAppWindow(String title){
        super(title);

        doctorTable.setModel(new DoctorTModel(dbController.getDoctors()));
        patientTable.setModel(new PatientTModel(dbController.getPatients()));
        visitTable.setModel(new VisitTModel(dbController.getVisits()));

        setMinimumSize(new Dimension(600,400));
        setContentPane(MainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        MainAppWindow app = new MainAppWindow("JDBC");
    }
}
