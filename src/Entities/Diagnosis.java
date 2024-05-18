package Entities;

import java.time.LocalDate;

public class Diagnosis {
    private Patients patient;
    private Doctors doctor;
    private Diseases disease;
    private LocalDate date;
    private Boolean closed;

    public Diagnosis(Patients patient, Doctors doctor, Diseases disease) {
        this.patient = patient;
        this.doctor = doctor;
        this.disease = disease;
        this.date = LocalDate.now();
        this.closed = false;
    }

    public Patients getPatient() {
        return patient;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public Diseases getDisease() {
        return disease;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getClosed() {
        return closed;
    }
}
