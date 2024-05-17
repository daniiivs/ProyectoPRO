package Entities;

import java.time.LocalDate;

public class Diagnosis {
    private Patients patient;
    private Doctors doctor;
    private Diseases disease;
    private LocalDate date;
    private Boolean active;

    public Diagnosis(Patients patient, Doctors doctor, Diseases disease) {
        this.patient = patient;
        this.doctor = doctor;
        this.disease = disease;
        this.date = LocalDate.now();
        this.active = true;
    }
}
