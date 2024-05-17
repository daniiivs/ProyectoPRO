package Entities;

public class Diseases {
    private Integer id;
    private String name;
    private String description;
    private Systems system;

    public Diseases(Integer id, String name, String description, Systems system) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.system = system;
    }
}
