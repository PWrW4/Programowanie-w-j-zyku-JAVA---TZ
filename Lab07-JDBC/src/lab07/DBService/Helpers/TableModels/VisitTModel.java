package lab07.DBService.Helpers.TableModels;

import lab07.DBService.Helpers.Patient;
import lab07.DBService.Helpers.Visit;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class VisitTModel extends AbstractTableModel {
    ArrayList<Visit> visit;
    private String[] columns;

    public VisitTModel(ArrayList<Visit> tab){
        super();
        visit = tab;
        columns = new String[]{"ID","Room","Finished","Time","Doctor", "Patient"};
    }

    @Override
    public int getRowCount() {
        return visit.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Visit v = visit.get(rowIndex);

        switch(columnIndex) {
            case 0: return v.getId();
            case 1: return v.getRoom();
            case 2: if(v.getFinished()==0)return "False"; else return "True";
            case 3: return v.getTime().toString();
            case 4: return v.getDoctor().getName()+ " " + v.getDoctor().getSurname();
            case 5: return v.getPatient().getName()+ " " + v.getPatient().getSurname();
            default: return null;
        }
    }

    public String getColumnName(int col) {
        return columns[col] ;
    }
}
