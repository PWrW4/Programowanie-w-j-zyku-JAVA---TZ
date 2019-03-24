package lab04;

import javax.swing.*;
import java.awt.*;

public class NoteBean extends JPanel {

    public NoteBean() {
    }

    String noteTitle = "My NoteBean";
    int startDate = 0;
    int endDate = 5;

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
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

    @Override
    protected void paintComponent(Graphics g) {

    }


}
