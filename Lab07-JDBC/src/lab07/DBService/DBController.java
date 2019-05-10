package lab07.DBService;

import lab07.DBService.Helpers.Doctor;
import lab07.DBService.Helpers.DoctorType;
import lab07.DBService.Helpers.Patient;
import lab07.DBService.Helpers.Visit;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBController {

    private Connection conn = null;

    DBController(){

        String url = "jdbc:sqlite:./db.db";

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created/connected.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        createSchama();

    }

    private void createSchama(){
        try {
            String doctors = "CREATE TABLE IF NOT EXISTS doctors (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL,\n"
                    + "	surname text NOT NULL,\n"
                    + "	type text NOT NULL\n"
                    + ");";
            Statement stm = conn.createStatement();
            stm.execute(doctors);

            String patients = "CREATE TABLE IF NOT EXISTS patients (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL,\n"
                    + "	surname text NOT NULL\n"
                    + ");";
            stm = conn.createStatement();
            stm.execute(patients);

            String visits = "CREATE TABLE IF NOT EXISTS visits (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	room integer NOT NULL,\n"
                    + "	finished integer NOT NULL,\n"
                    + "	time text NOT NULL,\n"
                    + "	doctor_id integer NOT NULL,\n"
                    + "	patient_id integer NOT NULL,\n"
                    + "	FOREIGN KEY (patient_id) REFERENCES patients(id),\n"
                    + "	FOREIGN KEY (doctor_id) REFERENCES doctors(id)\n"
                    + ");";
            stm = conn.createStatement();
            stm.execute(visits);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPatient(String name, String surname){
        String sql = "INSERT INTO patients(name,surname) VALUES(?,?)";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDoctor(String name, String surname, DoctorType type){
        String sql = "INSERT INTO doctors(name,surname,type) VALUES(?,?,?)";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, type.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addVisit(int room, int finished, Date date, int doctor_id, int patient_id){
        String sql = "INSERT INTO visits(room,finished,time,doctor_id,patient_id) VALUES(?,?,?,?,?)";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, room);
            pstmt.setInt(2, finished);
            pstmt.setString(3, date.toString());
            pstmt.setInt(4, doctor_id);
            pstmt.setInt(5, patient_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePatient(int id, String name, String surname){
        String sql = "UPDATE  patients SET"
                + " name = ?,"
                + " surname = ?"
                + " WHERE id = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setInt(3,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDoctor(int id, String name, String surname, DoctorType type){
        String sql = "UPDATE  doctors SET"
                + " name = ?,"
                + " surname = ?,"
                + " type = ?"
                + " WHERE id = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, type.toString());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateVisit(int id, int room, int finished, Date date, int doctor_id, int patient_id){
        String sql = "UPDATE  visits SET"
                + " room = ?,"
                + " finished = ?,"
                + " time = ?,"
                + " doctor_id = ?,"
                + " patient_id = ?"
                + " WHERE id = ?";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, room);
            pstmt.setInt(2, finished);
            pstmt.setString(3, date.toString());
            pstmt.setInt(4, doctor_id);
            pstmt.setInt(5, patient_id);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDoctor(int id) {
        String sql = "DELETE FROM doctors WHERE id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteVisit(int id) {
        String sql = "DELETE FROM visits WHERE id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Visit> getVisits(){
        String sql = "SELECT id, room, finished, time, doctor_id, patient_id FROM visits";
        ArrayList<Visit> ret = new ArrayList<Visit>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ret.add(new Visit(rs.getInt("id"), rs.getInt("room"),rs.getInt("finished"),new Date(),getDoctorID(rs.getInt("doctor_id")), getPatientID(rs.getInt("patient_id"))));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public ArrayList<Doctor> getDoctors(){
        String sql = "SELECT id, name, surname, type FROM doctors";
        ArrayList<Doctor> ret = new ArrayList<Doctor>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ret.add(new Doctor(rs.getInt("id"),rs.getString("neme"),rs.getString("surname"),Enum.valueOf(DoctorType.class,rs.getString("type"))));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public ArrayList<Patient> getPatients(){
        String sql = "SELECT id, name, surname FROM patients";
        ArrayList<Patient> ret = new ArrayList<Patient>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ret.add(new Patient(rs.getInt("id"),rs.getString("neme"),rs.getString("surname")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public Visit getVisitID(int id){
        String sql = "SELECT id, room, finished, time, doctor_id, patient_id FROM visits WHERE id = ?";
        Visit v = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                v = new Visit(rs.getInt("id"), rs.getInt("room"),rs.getInt("finished"),new Date(),getDoctorID(rs.getInt("doctor_id")), getPatientID(rs.getInt("patient_id")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    public Doctor getDoctorID(int id){
        String sql = "SELECT id, name, surname, type FROM doctors WHERE id = ?";
        Doctor d = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            d = new Doctor(rs.getInt("id"),rs.getString("neme"),rs.getString("surname"),Enum.valueOf(DoctorType.class,rs.getString("type")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return d;
    }

    public Patient getPatientID(int id){
        String sql = "SELECT id, name, surname FROM patients WHERE id = ?";
        Patient p = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            p = new Patient(rs.getInt("id"),rs.getString("neme"),rs.getString("surname"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public static void main(String[] args) {
        DBController db = new DBController();
        db.addPatient("Wojtek", "W");
        db.updatePatient(1,"Wojtek", "Wojcik");
        db.addDoctor("Wojtek", "W", DoctorType.Chirurg);
        db.updateDoctor(1,"Wojtek", "W", DoctorType.Internista);
        db.addVisit(121,0,new Date(),0,0);
        db.updateVisit(1,131,0,new Date(),0,0);

        db.deleteDoctor(3);
        db.deletePatient(3);
        db.deleteVisit(3);
    }

}
