package UI;

import Entities.Database;
import Entities.Doctors;
import Entities.Speciality;

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

    public static Speciality getSpecialityId(String specialityInfo) {
        String specialityId = specialityInfo.split("-")[0].trim();
        for (Speciality speciality : getSpecialityList()) {
            if (speciality.getId().equals(specialityId)) {
                return speciality;
            }
        }
        return null;
    }

    public static ArrayList<Speciality> getSpecialityList(){
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

    public static boolean DNIisRegistered(String dni) {
        ResultSet results = database.selectDoctors();
        try {
            while (results.next()) {
                if (results.getString("dni").equals(dni)){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNewDoctor(String dni, String name, String password, String firstLastName, String secondLastName, Speciality speciality) {
        database.insertDoctor(new Doctors(dni, name, password, firstLastName, secondLastName, speciality));
    }
}
