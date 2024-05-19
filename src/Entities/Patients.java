package Entities;

public class Patients extends Person {
    private String city;
    private Integer phoneNumber;

    public Patients(String dni, String name, String firstLastName, String secondLastName, String city, Integer phoneNumber) {
        super(dni, name, firstLastName, secondLastName);
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
