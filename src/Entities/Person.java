package Entities;

/**
 * Abstract Class for Person
 */
public abstract class Person {
    private String dni;
    private String name;
    private String firstLastName;
    private String secondLastName;

    /**
     * Constructor for Person with all parameters
     * @param dni person's DNI
     * @param name person's name
     * @param firstLastName person's first last name
     * @param secondLastName person's second last name
     */
    public Person(String dni, String name, String firstLastName, String secondLastName) {
        this.dni = dni;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    /**
     * Empty constructor for Person
     */
    public Person() {
    }

    /**
     * Getter for the person's DNI
     * @return person's DNI
     */
    public String getDni() {
        return dni;
    }

    /**
     * Setter for the person's DNI
     * @param dni person's DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Getter for the person's name
     * @return person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the person's name
     * @param name person's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the person's first last name
     * @return person's first last name
     */
    public String getFirstLastName() {
        return firstLastName;
    }

    /**
     * Setter for the person's first last name
     * @param firstLastName person's first last name
     */
    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    /**
     * Getter for the person's second last name
     * @return person's second last name
     */
    public String getSecondLastName() {
        return secondLastName;
    }

    /**
     * Setter for the person's second last name
     * @param secondLastName person's second last name
     */
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
}
