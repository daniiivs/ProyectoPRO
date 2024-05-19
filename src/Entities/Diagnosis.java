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

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    public Diseases getDisease() {
        return disease;
    }

    public void setDisease(Diseases disease) {
        this.disease = disease;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
