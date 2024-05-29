package DatabaseConnection;

import Entities.Diagnosis;
import Entities.Doctors;
import UI.WelcomeFrame;

import javax.swing.*;
import java.io.*;
import java.sql.*;

/**
 * Class for Database, which is responsible for connecting to the database and executing querys
 */
public class Database {
    private File connectionFile;

    private final String IP;
    private final String PORT;
    private final String DATABASE;
    private final String USER;
    private final String PASSWORD;
    private Connection connection;

    /**
     * Constructor for Database. It gets all the parameters needed from the file called "DatabaseInfo.txt"
     */
    public Database() {
        connectionFile = new File("src/DatabaseConnection/DatabaseInfo.txt");
        this.IP = getInfoFromFile("ip", connectionFile);
        this.PORT = getInfoFromFile("port", connectionFile);
        this.DATABASE = getInfoFromFile("db", connectionFile);
        this.USER = getInfoFromFile("user", connectionFile);
        this.PASSWORD = getInfoFromFile("password", connectionFile);

        connectToDatabase();
    }

    /**
     * Method that starts the connection with the database, which is set as the connection parameter of the class
     */
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "No se encuentra la clase en cuestión", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error al conectarse a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method that gets the value of a specified field contained within a file. The name of the file and its value are divided by a '$'
     * @param field name of the field to search for
     * @param file file in which the information is stored
     * @return the value of the given file
     */
    public String getInfoFromFile(String field, File file) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                if (field.equals(line.split("\\$")[0])) {
                    if (line.split("\\$")[1].equals("none")) {
                        return "";
                    }
                    return line.split("\\$")[1];
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return null;
    }

    /**
     * Method to execute a given query
     * @param query query to be executed. It can be an insert, delete or update
     */
    public void generalQuery(String query) {
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to insert a new diagnosis record in the database
     * @param diagnosis diagnosis to be inserted
     */
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
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to insert a new doctor record in the database
     * @param doctor doctor to be inserted
     */
    public void insertDoctor(Doctors doctor) {
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
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to select a doctor's password from their DNI
     * @param dni doctor's DNI
     * @return doctor's password
     */
    public String selectPassword(String dni) {
        try {
            PreparedStatement getPassword = this.connection.prepareStatement("SELECT passwd FROM medico WHERE dni LIKE ?");
            getPassword.setString(1, dni);
            ResultSet result = getPassword.executeQuery();
            if (result.next()) { // This will only have one result, since DNIs cannot be repeated
                return result.getString("passwd");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to selects all the specialities registered in the database
     * @return the ResultSet of the SELECT query, containing all specialities
     */
    public ResultSet selectSpecialities() {
        try {
            Statement getSpecialities = this.connection.createStatement();
            return getSpecialities.executeQuery("SELECT * FROM especialidad");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to select all the patients registered in the database
     * @return the ResultSet of the SELECT query, containing all patients ordered by their last names
     */
    public ResultSet selectPatients() {
        try {
            Statement getPatients = this.connection.createStatement();
            return getPatients.executeQuery("SELECT dni, apellido1, apellido2, nombre, localidad, telefono FROM paciente ORDER BY apellido1, apellido2, nombre");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to select all the diseases registered in the database
     * @return the ResultSet of the SELECT query, containing all diseases
     */
    public ResultSet selectDiseases() {
        try {
            Statement getDiseases = this.connection.createStatement();
            return getDiseases.executeQuery("SELECT * FROM enfermedad ORDER BY nombre");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to get the ID of a disease, based on its name
     * @param diseaseName the name of the disease that will be searched
     * @return the ID of the given disease
     */
    public Integer getDiseaseIdByName(String diseaseName) {
        try {
            PreparedStatement getDisease = this.connection.prepareStatement("SELECT codigo FROM enfermedad WHERE nombre LIKE ?");
            getDisease.setString(1, diseaseName);
            ResultSet result = getDisease.executeQuery();
            if (result.next()){
                return result.getInt("codigo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to select all diagnosis tied to a specific doctor, based on whether the diagnosis is opened or closed (active or not)
     * @param condition if the diagnosis are closed (true) or opened (false)
     * @param user the doctor responsible for the diagnosis
     * @return the ResultSet of the SELECT query, which contains all the diagnosis tied to the doctor
     */
    public ResultSet selectDiagnosis(Boolean condition, Doctors user) {
        try {
            PreparedStatement getDiagnosis = this.connection.prepareStatement("SELECT diagnostico.dni_paciente, concat(paciente.apellido1, ' ', paciente.apellido2, ', ', paciente.nombre) " +
                    "as paciente, enfermedad.nombre, paciente.telefono, diagnostico.fecha_diagnostico as enfermedad FROM diagnostico INNER JOIN paciente ON diagnostico.dni_paciente LIKE " +
                    "paciente.dni INNER JOIN enfermedad on diagnostico.codigo_enfermedad LIKE enfermedad.codigo WHERE alta LIKE ? AND diagnostico.dni_medico LIKE ?");
            getDiagnosis.setBoolean(1, condition);
            getDiagnosis.setString(2, user.getDni());
            return getDiagnosis.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to select a specified diagnosis from the database, which will be used to determine whether if that diagnosis has been registered in the database
     * @param diagnosis the diagnosis that's going to be searched
     * @return the ResultSet of the SELECT query, containing the information of the diagnosis (if it exists)
     */
    public ResultSet selectDiagnosisByPrimaryKey(Diagnosis diagnosis) {
        try {
            PreparedStatement getDoctor = this.connection.prepareStatement("SELECT * from diagnostico WHERE dni_paciente LIKE ? AND " +
                    "dni_medico LIKE ? AND codigo_enfermedad LIKE ? AND fecha_diagnostico LIKE ?");
            getDoctor.setString(1, diagnosis.getPatient().getDni());
            getDoctor.setString(2, diagnosis.getDoctor().getDni());
            getDoctor.setInt(3, diagnosis.getDisease().getId());
            getDoctor.setDate(4, Date.valueOf(diagnosis.getDate()));
            return getDoctor.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to select a specified doctor from the database, based on their DNI
     * @param dni the DNI of the doctor, which will be used to look for them in the database
     * @return the ResultSet of the SELECT query, containing the information of the doctor (if it exists)
     */
    public ResultSet selectDoctorByDNI(String dni) {
        try {
            PreparedStatement getDoctor = this.connection.prepareStatement("SELECT medico.*, especialidad.nombre as nombreEspecialidad FROM medico " +
                    "INNER JOIN especialidad ON medico.especialidad LIKE especialidad.codigo WHERE dni LIKE ?");
            getDoctor.setString(1, dni);
            return getDoctor.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method to update a given diagnosis, setting it to closed
     * @param dniPatient DNI of the patient involved in the diagnosis
     * @param dniDoctor DNI of the doctor involved in the diagnosis
     * @param diseaseId ID of the disease registered in the diagnosis
     * @param date date in which the diagnosis was created
     */
    public void updateCloseDiagnosis(String dniPatient, String dniDoctor, int diseaseId, String date) {
        try {
            PreparedStatement updateCloseDiagnosis = this.connection.prepareStatement("UPDATE diagnostico SET alta = true WHERE dni_paciente LIKE ? AND " +
                    "dni_medico LIKE ? AND codigo_enfermedad LIKE ? AND fecha_diagnostico LIKE ?");
            updateCloseDiagnosis.setString(1, dniPatient);
            updateCloseDiagnosis.setString(2, dniDoctor);
            updateCloseDiagnosis.setInt(3, diseaseId);
            updateCloseDiagnosis.setString(4, date);
            updateCloseDiagnosis.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to delete a given closed diagnosis from the database
     * @param dniPatient DNI of the patient involved in the diagnosis
     * @param dniDoctor DNI of the doctor involved in the diagnosis
     * @param diseaseId ID of the disease registered in the diagnosis
     * @param date date in which the diagnosis was created
     */
    public void deleteClosedDiagnosis(String dniPatient, String dniDoctor, int diseaseId, String date) {
        try {
            PreparedStatement deleteClosedDiagnosis = this.connection.prepareStatement("DELETE FROM diagnostico WHERE dni_paciente LIKE ? AND " +
                    "dni_medico LIKE ? AND codigo_enfermedad LIKE ? AND fecha_diagnostico LIKE ?");
            deleteClosedDiagnosis.setString(1, dniPatient);
            deleteClosedDiagnosis.setString(2, dniDoctor);
            deleteClosedDiagnosis.setInt(3, diseaseId);
            deleteClosedDiagnosis.setString(4, date);
            deleteClosedDiagnosis.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
