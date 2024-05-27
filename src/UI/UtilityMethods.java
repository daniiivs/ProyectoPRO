package UI;

import DatabaseConnection.Database;
import Entities.*;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class containing static methods, that will act as a controller between the UI and the database
 */
public class UtilityMethods {
    static final Database database = new Database(); // New instance of the database, which will be used to execute its methods

    /**
     * Method that checks if the database has any diseases registered. If it doesn't, it will add them using generalQuery()
     */
    public static void checkDiseases(){
        try {
            if (!database.selectDiseases().next()) { // Checks if the ResultSet given by selectDiseases() is empty. If it is, it will execute the insert
                JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Vaya, parece que las enfermedades no se encuentran registradas en la base de datos...");
                database.generalQuery(readFile(new File("src/DatabaseConnection/Diseases.txt")));
                JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Se han añadido todas las enfermedades");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to read a file and create a String with its content, dividing each line by a black space
     * @param file file that will be read
     * @return string with the content of the file
     */
    public static String readFile(File file) {
        StringBuilder content = new StringBuilder();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                content.append(line).append(" ");
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "No se encuentra el fichero indicado", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return content.toString();
    }

    /**
     * Method that checks if a given DNI is valid or not
     * @param dni DNI that will be checked
     * @return if the DNI is valid (true) or not (false)
     */
    public static boolean checkDni(String dni) {
        String check = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (dni.length() != 9) {
            return false;
        }
        return (check.charAt(Integer.parseInt(dni.substring(0, 8)) % check.length()) == dni.charAt(8));
    }

    /**
     * Method that checks if two strings are the same
     * @param stringA the first string to compare
     * @param stringB the second string to compare
     * @return if the strings match (true) or not (false)
     */
    public static boolean checkMatch(String stringA, String stringB) {
        return stringA.equals(stringB);
    }

    /**
     * Method that splits the information in the speciality combobox by the '-', getting its ID. For example, "ALE - ALERGOLOGIA" will return 'ALE'
     * @param specialityInfo the String from the selected option of the speciality combobox
     * @return the Speciality that has the given ID
     */
    public static Speciality splitSpeciality(String specialityInfo) {
        String specialityId = specialityInfo.split("-")[0].trim();
        for (Speciality speciality : getSpecialityList()) {
            if (speciality.getId().equals(specialityId)) {
                return speciality;
            }
        }
        return null;
    }

    /**
     * Method that splits the information in the patients combobox by the '-', getting its DNI
     * @param patientInfo the String from the selected option of the patient combobox
     * @return the patient that has the given DNI
     */
    public static Patients splitPatient(String patientInfo) {
        String patientDni = patientInfo.split("-")[1].trim();
        for (Patients patient : getPatientsList()) {
            if (patient.getDni().equals(patientDni)) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Method that splits the information in the disease combobox by the '-', getting its ID
     * @param diseaseInfo the String from the selected option of the disease combobox
     * @return the disease that has the given ID
     */
    public static Diseases splitDisease(String diseaseInfo) {
        Integer diseaseId = Integer.valueOf(diseaseInfo.split("-")[1].trim());
        for (Diseases disease : getDiseaseList()) {
            if (disease.getId().equals(diseaseId)) {
                return disease;
            }
        }
        return null;
    }

    /**
     * Method that creates an ArrayList which contains all the diseases registered in the database
     * @return ArrayList containing all the diseases registered in database
     */
    public static ArrayList<Speciality> getSpecialityList() {
        ResultSet results = database.selectSpecialities();
        ArrayList<Speciality> specialitiesList = new ArrayList<>();
        try {
            while (results.next()) {
                specialitiesList.add(new Speciality(results.getString("codigo"), results.getString("nombre")));
            }
            return specialitiesList;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method that creates an ArrayList which contains all the patients registered in the database
     * @return ArrayList containing all the patients registered in database
     */
    public static ArrayList<Patients> getPatientsList() {
        ResultSet results = database.selectPatients();
        ArrayList<Patients> patientsList = new ArrayList<>();
        try {
            while (results.next()) {
                patientsList.add(new Patients(results.getString("dni"), results.getString("nombre"), results.getString("apellido1"), results.getString("apellido2"), results.getString("localidad") , results.getInt("telefono")));
            }
            return patientsList;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method that creates an ArrayList which contains all the diseases registered in the database
     * @return ArrayList containing all the diseases registered in database
     */
    public static ArrayList<Diseases> getDiseaseList() {
        ResultSet results = database.selectDiseases();
        ArrayList<Diseases> diseasesList = new ArrayList<>();
        try {
            while (results.next()) {
                diseasesList.add(new Diseases(results.getInt("codigo"), results.getString("nombre"), results.getString("descripcion")));
            }
            return diseasesList;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Ha habido un error durante el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Method that checks if there's a doctor registered in the database with the given DNI
     * @param dni DNI that will be checked
     * @return if the DNI is already asigned to a doctor (true) or not (false)
     */
    public static boolean dniIsRegistered(String dni) {
        ResultSet results = database.selectDoctorByDNI(dni);
        try {
            return results.next(); // If the query gave any results, this will return true. Otherwise, it will be false
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to add a new doctor into the database. It acts as a "bridge" between the UI and the database
     * @param dni doctor's DNI
     * @param name doctor's name
     * @param password doctor's password
     * @param firstLastName doctor's first last name
     * @param secondLastName doctor's second last name
     * @param speciality doctor's speciality
     */
    public static void addNewDoctor(String dni, String name, String password, String firstLastName, String secondLastName, Speciality speciality) {
        database.insertDoctor(new Doctors(dni, name, password, firstLastName, secondLastName, speciality));
    }

    /**
     * Method to add a new diagnosis into the database. It acts as a "bridge" between the UI and the database
     * @param diagnosis diagnosis that's going to be added
     */
    public static void addNewDiagnosis(Diagnosis diagnosis) {
        database.insertDiagnosis(diagnosis);
    }

    /**
     * Method to check if the given password matches the actual password registered in the database, from a specific doctor
     * @param password password typed in the password field from the log in window
     * @param dni doctor's dni, typped in the dni field from the log in window
     * @return if the passwords match (true) or not (false)
     */
    public static boolean passwordMatching(String password, String dni) {
        String realPassword = database.selectPassword(dni);
        return realPassword.equals(password);
    }

    /**
     * Method that gets all the diagnosis from a specific doctor, specifying whether they are still active (opened) or not (closed)
     * @param condition if the diagnosis are opened (false) or closed (true)
     * @param user the doctor whose diagnoses will be selected
     * @return the ResultSet of the SELECT query, containing all the opened/closed diagnosis from the given doctor
     */
    public static ResultSet diagnosisInformation(Boolean condition, Doctors user) {
        return database.selectDiagnosis(condition, user);
    }

    /**
     * Method that gets all the patients from the database
     * @return the ResultSet containing the information form all patients registered in the database
     */
    public static ResultSet patientsInformation() {
        return database.selectPatients();
    }

    /**
     * Method that checks if a given diagnosis is already registered in the database
     * @param diagnosis the diagnosis that will be checked
     * @return if the diagnosis already exists (true) or not (false)
     */
    public static boolean diagnosisExists(Diagnosis diagnosis) {
        try {
            return database.selectDiagnosisByPrimaryKey(diagnosis).next(); // If the SELECT query gives at least one result, this will return true. If not, it will return false
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that builds a multidimensional array based on a ResultSet from a specified SELECT. This multidimensional will later be used to build the different tables
     * @param resultSet ResultSet used to build the multidimensional array
     * @return multidimensional array containing all the information from the ResultSet
     */
    public static String[][] buildTable(ResultSet resultSet){
        ArrayList<String[]> resultSetList = new ArrayList<>(); // This ArrayList will contain all the records from the ResultSet
        String[] record; // This will be used to store the information of each column from a record
        String[][] tableData; // This will be the multidimensional array used to store all the records

        try {
            while (resultSet.next()) {
                record = new String[resultSet.getMetaData().getColumnCount()]; // This gives the amount of columns a record has
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    record[i] = resultSet.getString(i + 1);
                }
                resultSetList.add(record);
            }
            if (resultSetList.isEmpty()){ // If the ResultSet is empty (which means there is no information), the record will be null
                record = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    record[i] = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        tableData = new String[resultSetList.size()][]; // The multidimensional array will have as many rows as elements the list has
        for (String[] row : resultSetList) {
            tableData[i] = row;
            i++;
        }
        return tableData;
    }

    /**
     * Method to log in the app after giving a specific DNI. This method will create a new LoggedInFrame instance, using the given DNI to create a new doctor and sending it as the user parameter
     * @param dni the DNI of the doctor that is going to log in
     */
    public static void logIn(String dni) {
        ResultSet result = database.selectDoctorByDNI(dni);
        try {
            while (result.next()) {
                Speciality speciality = new Speciality(result.getString("especialidad"), result.getString("nombreEspecialidad"));
                Doctors user = new Doctors(result.getString("dni"), result.getString("nombre"), result.getString("passwd"), result.getString("apellido1"), result.getString("apellido2"), speciality);
                WelcomeFrame.welcomeFrame.closeFrame();
                new LoggedInFrame(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to close a chosen diagnosis from the table
     * @param loggedUser doctor who is logged in
     * @param dniPatient DNI from the patient whose diagnosis was selected
     * @param diseaseName name from the disease related to the selected diagnosis
     * @param date date in which the selected diagnosis was created
     */
    public static void closeDiagnosis(Doctors loggedUser, String dniPatient, String diseaseName, String date) {
        database.updateCloseDiagnosis(dniPatient, loggedUser.getDni(), database.getDiseaseIdByName(diseaseName), date);
    }

    /**
     * Method to delete a chosen diagnosis from the table
     * @param loggedUser doctor who is logged in
     * @param dniPatient DNI from the patient whose diagnosis was selected
     * @param diseaseName name from the disease related to the selected diagnosis
     * @param date date in which the selected diagnosis was created
     */
    public static void deleteDiagnosis(Doctors loggedUser, String dniPatient, String diseaseName, String date) {
        database.deleteClosedDiagnosis(dniPatient, loggedUser.getDni(), database.getDiseaseIdByName(diseaseName), date);
    }
}