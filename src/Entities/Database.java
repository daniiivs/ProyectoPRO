package Entities;

import java.sql.*;

public class Database {
    private String ip = "localhost";
    private String port = "3306";
    private String database = "hospitalPRO";
    private String usuario = "root";
    private String password = "";
    private Connection connection;

    public Database () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database, usuario, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertDoctor(Doctors doctor){
        try {
            PreparedStatement insertDoctor = this.connection.prepareStatement("INSERT INTO medico VALUES (?, ?, ?, ?, ?, ?)");
            insertDoctor.setString(1, doctor.getDni());
            insertDoctor.setString(2, doctor.getPassword());
            insertDoctor.setString(3, doctor.getName());
            insertDoctor.setString(4, doctor.getFirstLastName());
            insertDoctor.setString(5, doctor.getSecondLastName());
            insertDoctor.setString(6, doctor.getSpeciality().getId());
            insertDoctor.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectSpecialities() {
        try {
            Statement getSpecialities = this.connection.createStatement();
            return getSpecialities.executeQuery("SELECT * FROM especialidad");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectDoctors() {
        try {
            Statement getSpecialities = this.connection.createStatement();
            return getSpecialities.executeQuery("SELECT * FROM medico");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
