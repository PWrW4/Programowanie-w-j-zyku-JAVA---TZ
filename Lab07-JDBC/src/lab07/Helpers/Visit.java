package lab07.Helpers;

import java.util.Date;

public class Visit {

    private int id;
    private int room;
    private int finished;
    private Date time;
    private Doctor doctor;
    private Patient patient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Visit(int id, int room, int finished, Date time, Doctor doctor, Patient patient) {
        this.id = id;
        this.room = room;
        this.finished = finished;
        this.time = time;
        this.doctor = doctor;
        this.patient = patient;
    }
}
