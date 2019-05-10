package lab07.DBService.Helpers.TableModels;

import lab07.DBService.Helpers.Doctor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorTModel extends AbstractTableModel {

    ArrayList<Doctor> doctors;
    private String[] columns;

    public DoctorTModel(ArrayList<Doctor> tab){
        super();
        doctors = tab;
        columns = new String[]{"ID","Name","Surname","Type"};
    }

    @Override
    public int getRowCount() {
        return doctors.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Doctor d = doctors.get(rowIndex);

        switch(columnIndex) {
            case 0: return d.getId();
            case 1: return d.getName();
            case 2: return d.getSurname();
            case 3: return d.getType().toString();
            default: return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }

}
