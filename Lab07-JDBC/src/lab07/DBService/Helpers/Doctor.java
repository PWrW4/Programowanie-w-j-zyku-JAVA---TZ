package lab07.DBService.Helpers;


import lab07.DBService.Helpers.Emums.DoctorType;

public class Doctor {

    private int id;
    private String name;

    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public DoctorType getType() {
        return type;
    }

    public void setType(DoctorType type) {
        this.type = type;
    }

    private DoctorType type;

    public Doctor(int id, String name, String surname, DoctorType type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " " +surname + " " + type.toString();
    }
}
