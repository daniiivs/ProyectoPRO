package UI;

import DatabaseConnection.Database;
import Entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilityMethods {
    static Database database = new Database();

    public static boolean checkDni(String dni) {
        String check = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (dni.length() != 9) {
            return false;
        }
        return (check.charAt(Integer.parseInt(dni.substring(0, 8)) % check.length()) == dni.charAt(8));
    }

    public static boolean checkMatch(String stringA, String stringB) {
        return stringA.equals(stringB);
    }

    public static Speciality splitSpeciality(String specialityInfo) {
        String specialityId = specialityInfo.split("-")[0].trim();
        for (Speciality speciality : getSpecialityList()) {
            if (speciality.getId().equals(specialityId)) {
                return speciality;
            }
        }
        return null;
    }

    public static Patients splitPatient(String patientInfo) {
        String patientDni = patientInfo.split("-")[1].trim();
        for (Patients patient : getPatientsList()) {
            if (patient.getDni().equals(patientDni)) {
                return patient;
            }
        }
        return null;
    }

    public static Diseases splitDisease(String diseaseInfo) {
        Integer diseaseId = Integer.valueOf(diseaseInfo.split("-")[1].trim());
        for (Diseases disease : getDiseaseList()) {
            if (disease.getId().equals(diseaseId)) {
                return disease;
            }
        }
        return null;
    }

    public static ArrayList<Speciality> getSpecialityList() {
        ResultSet results = database.selectSpecialities();
        ArrayList<Speciality> specialitiesList = new ArrayList<>();
        try {
            while (results.next()) {
                specialitiesList.add(new Speciality(results.getString("codigo"), results.getString("nombre")));
            }
            return specialitiesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Patients> getPatientsList() {
        ResultSet results = database.selectPatients();
        ArrayList<Patients> patientsList = new ArrayList<>();
        try {
            while (results.next()) {
                patientsList.add(new Patients(results.getString("dni"), results.getString("nombre"), results.getString("apellido1"), results.getString("apellido2"), results.getString("localidad") , results.getInt("telefono")));
            }
            return patientsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Diseases> getDiseaseList() {
        ResultSet results = database.selectDiseases();
        ArrayList<Diseases> diseasesList = new ArrayList<>();
        try {
            while (results.next()) {
                diseasesList.add(new Diseases(results.getInt("codigo"), results.getString("nombre"), results.getString("descripcion")));
            }
            return diseasesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean dniIsRegistered(String dni) {
        ResultSet results = database.selectDoctorByDNI(dni);
        try {
            return results.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNewDoctor(String dni, String name, String password, String firstLastName, String secondLastName, Speciality speciality) {
        database.insertDoctor(new Doctors(dni, name, password, firstLastName, secondLastName, speciality));
    }

    public static void addNewDiagnosis(Diagnosis diagnosis) {
        database.insertDiagnosis(diagnosis);
    }

    public static boolean passwordNotMatching(String password, String dni) {
        String realPassword = database.selectPassword(dni);
        return realPassword.equals(password);
    }

    public static ResultSet diagnosisInformation(Boolean condition, Doctors user) {
        return database.selectDiagnosis(condition, user);
    }

    public static ResultSet patientsInformation() {
        return database.selectPatients();
    }

    public static boolean diagnosisExists(Diagnosis diagnosis) {
        try {
            return database.selectDiagnosisByPrimaryKey(diagnosis).next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[][] buildTable(ResultSet resultSet){
        ArrayList<String[]> resultSetList = new ArrayList<>();
        String[] register;
        String[][] tableData;

        try {
            while (resultSet.next()) {
                register = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    register[i] = resultSet.getString(i + 1);
                }
                resultSetList.add(register);
            }
            if (resultSetList.isEmpty()){
                register = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    register[i] = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        tableData = new String[resultSetList.size()][];
        for (String[] row : resultSetList) {
            tableData[i] = row;
            i++;
        }
        return tableData;
    }

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

    public static void closeDiagnosis(Doctors loggedUser, String dniPatient, String diseaseName, String date) {
        database.updateCloseDiagnosis(dniPatient, loggedUser.getDni(), database.getDiseaseIdByName(diseaseName), date);
    }

    public static void deleteDiagnosis(Doctors loggedUser, String dniPatient, String diseaseName, String date) {
        database.deleteClosedDiagnosis(dniPatient, loggedUser.getDni(), database.getDiseaseIdByName(diseaseName), date);
    }
}