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

<<<<<<< Updated upstream:src/Entities/Database.java
=======
<<<<<<< Updated upstream:src/Model/Database.java
=======
>>>>>>> Stashed changes:src/Model/Database.java
    public String selectPassword(String dni) {
        try {
            PreparedStatement getPassword = this.connection.prepareStatement("SELECT passwd FROM medico WHERE dni LIKE ?");
            getPassword.setString(1, dni);
            ResultSet result = getPassword.executeQuery();
            while (result.next()) {
                return result.getString("passwd");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

<<<<<<< Updated upstream:src/Entities/Database.java
=======

    public void insertDiagnosis(Diagnosis diagnosis) {
        try {
            PreparedStatement insertDoctor = this.connection.prepareStatement("INSERT INTO diagnostico VALUES (?, ?, ?, ?, ?)");
            insertDoctor.setString(1, diagnosis.getPatient().getDni());
            insertDoctor.setString(2, diagnosis.getDoctor().getDni());
            insertDoctor.setInt(3, diagnosis.getDisease().getId());
            insertDoctor.setDate(4, Date.valueOf(diagnosis.getDate()));
            insertDoctor.setBoolean(5, diagnosis.getClosed());
            insertDoctor.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

>>>>>>> Stashed changes:src/Entities/Database.java
>>>>>>> Stashed changes:src/Model/Database.java
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

    public ResultSet selectPatients() {
        try {
<<<<<<< Updated upstream:src/Entities/Database.java
            Statement getDoctors = this.connection.createStatement();
            return getDoctors.executeQuery("SELECT * FROM medico");
=======
<<<<<<< Updated upstream:src/Model/Database.java
            Statement getSpecialities = this.connection.createStatement();
            return getSpecialities.executeQuery("SELECT * FROM medico");
=======
            Statement getPatients = this.connection.createStatement();
            return getPatients.executeQuery("SELECT dni, apellido1, apellido2, nombre, localidad, telefono FROM paciente ORDER BY apellido1, apellido2, nombre");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectDiseases() {
        try {
            Statement getDiseases = this.connection.createStatement();
            return getDiseases.executeQuery("SELECT * FROM enfermedad ORDER BY nombre");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectDiagnosisByPrimaryKey(Diagnosis diagnosis){
        try {
            PreparedStatement getDoctor = this.connection.prepareStatement("SELECT * from diagnostico WHERE dni_paciente LIKE ? AND " +
                    "dni_medico LIKE ? AND codigo_enfermedad LIKE ? AND fecha_diagnostico LIKE ?");
            getDoctor.setString(1, diagnosis.getPatient().getDni());
            getDoctor.setString(2, diagnosis.getDoctor().getDni());
            getDoctor.setInt(3, diagnosis.getDisease().getId());
            getDoctor.setDate(4, Date.valueOf(diagnosis.getDate()));
            return getDoctor.executeQuery();
>>>>>>> Stashed changes:src/Model/Database.java
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectDoctorByDNI(String dni) {
        try {
            PreparedStatement getDoctor = this.connection.prepareStatement("SELECT medico.*, especialidad.nombre as nombreEspecialidad FROM medico " +
                    "INNER JOIN especialidad ON medico.especialidad LIKE especialidad.codigo WHERE dni LIKE ?");
            getDoctor.setString(1, dni);
            return getDoctor.executeQuery();
<<<<<<< Updated upstream:src/Entities/Database.java
=======
>>>>>>> Stashed changes:src/Entities/Database.java
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectDiagnosis(Boolean condition) {
        try {
            PreparedStatement getDiagnosis = this.connection.prepareStatement("SELECT diagnostico.dni_paciente, concat(paciente.apellido1, ' ', paciente.apellido2, ', ', paciente.nombre) " +
                    "as paciente, enfermedad.nombre, paciente.telefono, diagnostico.fecha_diagnostico as enfermedad FROM diagnostico INNER JOIN paciente ON diagnostico.dni_paciente LIKE " +
                    "paciente.dni INNER JOIN enfermedad on diagnostico.codigo_enfermedad LIKE enfermedad.codigo WHERE alta LIKE ?");
            getDiagnosis.setBoolean(1, condition);
            return getDiagnosis.executeQuery();
>>>>>>> Stashed changes:src/Model/Database.java
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
