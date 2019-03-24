package lab04;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Note extends JPanel {

    private JLabel titleLabel;
    private JLabel startTime;
    private JTextField textStart;
    private JLabel endTime;
    private JTextField textEnd;

    public Note() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        titleLabel = new JLabel(noteTitle);
        startTime = new JLabel("Start Date:");
        textStart = new JTextField("0");
        endTime = new JLabel("End Date:");
        textEnd = new JTextField("10");

        add(titleLabel);
        add(startTime);
        add(textStart);
        add(endTime);
        add(textEnd);

        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (NoteData data : notes){
            listModel.addElement(data.toString());
        }

//        listModel.addElement(notes.get(0).toString());
//        listModel.addElement(notes.get(1).toString());
//        listModel.addElement(notes.get(2).toString());

        this.add(new JList<>(listModel));
    }

    String noteTitle = "My Note";
    int startDate = 0;
    int endDate = 5;

    ArrayList<NoteData> notes = new ArrayList<NoteData>(){
        {
            add(new NoteData("Notatka 1", 1));
            add(new NoteData("Notatka 2", 5));
            add(new NoteData("Notatka 3", 7));
        }
    };

    private static final int LEFT = 0;
    private static final int CENTER = 1;
    private static final int RIGHT = 2;

    private int titlePosition = CENTER;


    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
        titleLabel.setText(noteTitle);
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getTitlePosition() {
        return titlePosition;
    }

    public void setTitlePosition(int titlePosition) {
        this.titlePosition = titlePosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


}
