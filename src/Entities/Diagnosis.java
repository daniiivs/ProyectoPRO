package Entities;

import java.time.LocalDate;

/**
 * Class for Diagnosis
 */
public class Diagnosis {
    private Patients patient;
    private Doctors doctor;
    private Diseases disease;
    private LocalDate date;
    private Boolean closed;

    /**
     * Constructor for Diagnosis with all parameters
     * @param patient patient involved in the diagnosis
     * @param doctor doctor involved the diagnosis
     * @param disease disease detected in the diagnosis
     */
    public Diagnosis(Patients patient, Doctors doctor, Diseases disease) {
        this.patient = patient;
        this.doctor = doctor;
        this.disease = disease;
        this.date = LocalDate.now(); // diagnosis is created with the current
        this.closed = false;
    }

    /**
     * Empty constructor for a custom diagnosis
     */
    public Diagnosis() {
    }

    /**
     * Getter for patient
     * @return patient involved in the diagnosis
     */
    public Patients getPatient() {
        return patient;
    }

    /**
     * Setter for patient
     * @param patient patient involved in the diagnosis
     */
    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    /**
     * Getter for doctor
     * @return doctor involved in the diagnosis
     */
    public Doctors getDoctor() {
        return doctor;
    }

    /**
     * Setter for doctor
      * @param doctor doctor involved in the diangosis
     */
    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    /**
     * Getter for the disease registered in the diagnosis
     * @return disease registered in the diagnosis
     */
    public Diseases getDisease() {
        return disease;
    }

    /**
     * Setter for the disease registered in the diagnosis
     * @param disease disease registered in the diagnosis
     */
    public void setDisease(Diseases disease) {
        this.disease = disease;
    }

    /**
     * Getter for the date in which the dianosis was created
     * @return date in which the diagnosis was created
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter for the date in which the diagnosis was created
     * @param date date in which the diagnosis was created
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for the state of the diagnosis, which can be closed (true) or opened (true)
     * @return the state of the diagnosis
     */
    public Boolean getClosed() {
        return closed;
    }

    /**
     * Setter for the state of the diagnosis
     * @param closed the state of the diagnosis
     */
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
