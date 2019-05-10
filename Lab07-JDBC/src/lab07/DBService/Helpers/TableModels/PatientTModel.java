package lab07.DBService.Helpers.TableModels;

import lab07.DBService.Helpers.Doctor;
import lab07.DBService.Helpers.Patient;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PatientTModel extends AbstractTableModel {
    ArrayList<Patient> patient;
    private String[] columns;

    public PatientTModel(ArrayList<Patient> tab){
        super();
        patient = tab;
        columns = new String[]{"ID","Name","Surname"};
    }

    @Override
    public int getRowCount() {
        return patient.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient p = patient.get(rowIndex);

        switch(columnIndex) {
            case 0: return p.getId();
            case 1: return p.getName();
            case 2: return p.getSurname();
            default: return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}
