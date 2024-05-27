package Entities;

/**
 * Class for Diseases
 */
public class Diseases {
    private Integer id;
    private String name;
    private String description;

    /**
     * Constructor for Diseases with all parameters
     * @param id id of the disease
     * @param name name of the disease
     * @param description description of the disease
     */
    public Diseases(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Empty constructor for Diseases
     */
    public Diseases() {
    }

    /**
     * Getter for the ID of the disease
     * @return the ID of the disease
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for the ID of the disease
     * @param id ID of the disease
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for the name of the disease
     * @return name of the disease
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the disease
     * @param name name of the disease
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the description of the disease
     * @return description of the disease
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the disease
     * @param description description of the disease
     */
    public void setDescription(String description) {
        this.description = description;
    }
}