package Model;

public class Patients extends Person {
    private String city;
    private Integer phoneNumber;

    public Patients(String dni, String name, String password, String firstLastName, String secondLastName, String city, Integer phoneNumber) {
        super(dni, name, password, firstLastName, secondLastName);
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}
