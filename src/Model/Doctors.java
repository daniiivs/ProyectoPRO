package Model;

public class Doctors extends Person {
    private String speciality;

    public Doctors(String dni, String name, String password, String firstLastName, String secondLastName, String speciality) {
        super(dni, name, password, firstLastName, secondLastName);
        this.speciality = speciality;
    }
}
