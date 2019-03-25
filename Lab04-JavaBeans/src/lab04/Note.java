package lab04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Note extends JPanel {


    private String noteTitle = "My Note";
    private int startDate = 0;
    private int endDate = 5;

    private int minX = 250;
    private int minY = 550;


    private ArrayList<NoteData> notes = new ArrayList<NoteData>() {
        {
            add(new NoteData("Notatka 1", 1));
            add(new NoteData("Notatka 2", 5));
            add(new NoteData("Notatka 3", 7));
        }
    };


    private JLabel titleLabel;
    private JLabel startTime;
    private JTextField textStart;
    private JLabel endTime;
    private JTextField textEnd;
    private JButton filter;
    private JButton delete;
    private JList<String> listOfNotes;

    private JTextField addTitle;
    private JTextField addDate;
    private JButton add;


    private DefaultListModel<String> listModel;

    public Note() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        titleLabel = new JLabel(noteTitle);
        startTime = new JLabel("Start Date:");
        textStart = new JTextField("0");
        endTime = new JLabel("End Date:");
        textEnd = new JTextField("10");
        filter = new JButton("Show notes");
        delete = new JButton("Delete");
        addTitle = new JTextField("");
        addDate = new JTextField("");
        add = new JButton("Add");
        listOfNotes = new JList<>();

        titleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (NoteData data : notes) {
                    if (listOfNotes.getSelectedValue().equals(data.toString())) {
                        notes.remove(data);
                    }
                }

                listModel.clear();

                for (NoteData data : notes) {
                    if (data.getDate() > getStartDate() && data.getDate() < getEndDate()) {
                        listModel.addElement(data.toString());
                    }
                }

                listOfNotes.setModel(listModel);

            }
        });

        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setStartDate(Integer.parseInt(textStart.getText()));
                setEndDate(Integer.parseInt(textEnd.getText()));

                listModel.clear();

                for (NoteData data : notes) {
                    if (data.getDate() > getStartDate() && data.getDate() < getEndDate()) {
                        listModel.addElement(data.toString());
                    }
                }

                listOfNotes.setModel(listModel);

            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                notes.add(new NoteData(addTitle.getText(), Integer.parseInt(addDate.getText())));

                listModel.clear();

                for (NoteData data : notes) {
                    listModel.addElement(data.toString());
                }

                listOfNotes.setModel(listModel);
            }
        });


        titleLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        this.setMinimumSize(new Dimension(minX, minY));

        listModel = new DefaultListModel<String>();

        for (NoteData data : notes) {
            listModel.addElement(data.toString());
        }

        listOfNotes.setModel(listModel);


        add(titleLabel);
        add(startTime);
        add(textStart);
        add(endTime);
        add(textEnd);
        add(filter);
        add(new JScrollPane(listOfNotes));
        add(delete);
        add(addTitle);
        add(addDate);
        add(add);
    }


    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
        titleLabel.setText(noteTitle);
    }

    public ArrayList<NoteData> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<NoteData> notes) {
        this.notes = notes;
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

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
        this.setMinimumSize(new Dimension(minX, minY));
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
        this.setMinimumSize(new Dimension(minX, minY));
    }

    public static void main(String[] args) {
        new Note();
    }

}
