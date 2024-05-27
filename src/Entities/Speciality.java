package Entities;

/**
 * Class for Speciality
 */
public class Speciality {
    private String id;
    private String name;

    /**
     * Constructor for Speciality with all parameters
     * @param id ID of the disease
     * @param name name of the disease
     */
    public Speciality(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Empty constructor for Speciality
     */
    public Speciality() {
    }

    /**
     * Setter for the ID of the speciality
     * @param id ID of the speciality
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the ID of the speciality
     * @return ID of the speciality
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the name of the speciality
     * @param name name of the speciality
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of the speciality
     * @return name of the speciality
     */
    public String getName() {
        return name;
    }
}
