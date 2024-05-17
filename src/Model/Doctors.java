package Model;

public class Doctors extends Person {
    private String password;
    private Speciality speciality;

    public Doctors(String dni, String name, String password, String firstLastName, String secondLastName, Speciality speciality) {
        super(dni, name, firstLastName, secondLastName);
        this.password = password;
        this.speciality = speciality;
    }

    public String getPassword() {
        return password;
    }

    public Speciality getSpeciality() {
        return speciality;
    }
}
