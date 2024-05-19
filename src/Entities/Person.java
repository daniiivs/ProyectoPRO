package Entities;

public abstract class Person {
    private String dni;
    private String name;
    private String firstLastName;
    private String secondLastName;

    public Person(String dni, String name, String firstLastName, String secondLastName) {
        this.dni = dni;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }
}
