package lab04;

public class NoteData {

    private String note;
    private int date;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    NoteData(String note, int date){
        this.note = note;
        this.date = date;
    }

    @Override
    public String toString(){
        return note + " | Date: " + date;
    }
}
