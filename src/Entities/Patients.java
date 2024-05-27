package Entities;

/**
 * Class for Patients, which extends from Person
 */
public class Patients extends Person {
    private String city;
    private Integer phoneNumber;

    /**
     * Constructor for Patients with all parameters
     * @param dni patient's DNI
     * @param name patient's name
     * @param firstLastName patient's first last name
     * @param secondLastName patient's second last name
     * @param city patient's city
     * @param phoneNumber patient's phone number
     */
    public Patients(String dni, String name, String firstLastName, String secondLastName, String city, Integer phoneNumber) {
        super(dni, name, firstLastName, secondLastName);
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Empty constructor for Patients
     */
    public Patients() {
    }

    /**
     * Getter for the patient's city
     * @return patient's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the patient's city
     * @param city patient's city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for the patient's phone number
     * @return patient's phone number
     */
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for the patient's phone number
     * @param phoneNumber patient's phone number
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}