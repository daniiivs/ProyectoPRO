package Entities;

/**
 * Class for Doctors, which extends for Person
 */
public class Doctors extends Person {
    private String password;
    private Speciality speciality;

    /**
     * Constructor for Doctors with all parameters
     * @param dni doctor's DNI
     * @param name doctor's name
     * @param password doctor's password
     * @param firstLastName doctor's first last name
     * @param secondLastName doctor's second last name
     * @param speciality doctor's speciality
     */
    public Doctors(String dni, String name, String password, String firstLastName, String secondLastName, Speciality speciality) {
        super(dni, name, firstLastName, secondLastName);
        this.password = password;
        this.speciality = speciality;
    }

    /**
     * Empty constructor for Doctors
     */
    public Doctors() {
    }

    /**
     * Getter for the doctor's password
     * @return doctor's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the doctor's password
     * @param password doctor's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the doctor's speciality
     * @return doctor's speciality
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    /**
     * Setter for the doctor's speciality
     * @param speciality doctor's speciality
     */
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}