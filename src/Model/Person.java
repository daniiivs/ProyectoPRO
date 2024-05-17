package Model;

public abstract class Person {
    private String dni;
    private String name;
    private String password;
    private String firstLastName;
    private String secondLastName;

    public Person(String dni, String name, String password, String firstLastName, String secondLastName) {
        this.dni = dni;
        this.name = name;
        this.password = password;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }
}
