package UI;

import Entities.Diagnosis;
import Entities.Diseases;
import Entities.Doctors;
import Entities.Patients;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoggedInFrame extends JFrame {
    private Doctors loggedUser;

    private JTabbedPane contentPane;

    private JPanel mainInfoPanel;
    private JPanel activeDiagnosisPanel;
    private JPanel closedDiagnosisPanel;
    private JPanel addDiagnosisPanel;
    private JPanel allPatientsPanel;

    private JLabel nameLabel;
    private JLabel dniLabel;
    private JLabel passwordLabel;
    private JLabel specialityLabel;

    private JLabel patientLabel;
    private JLabel diseaseLabel;

    private JComboBox patientComboBox;
    private JComboBox diseaseComboBox;

    private JTable tableActive;
    private JTable tableClosed;
    private JTable tablePatients;

    private JButton addDiagnosisButton;

    String dniPatient;

    public LoggedInFrame(Doctors user) {
        this.loggedUser = user;

        editFrame();
        editPanels();
        editTables();
        editLabels();
        editInputs();
        editButtons();
        addTabbedPanels();

        JButton logOutButton = new JButton("Cerrar sesión");
        logOutButton.setBounds(238, 232, 119, 23);
        mainInfoPanel.add(logOutButton);
    }

    private void editButtons() {
        addDiagnosisButton = new JButton("Registrar Caso");
        addDiagnosisButton.setBounds(119, 200, 120, 23);
        addDiagnosisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Diagnosis diagnosis = new Diagnosis(UtilityMethods.splitPatient(String.valueOf(patientComboBox.getSelectedItem())), loggedUser, UtilityMethods.splitDisease(String.valueOf(diseaseComboBox.getSelectedItem())));
                if (UtilityMethods.diagnosisExists(diagnosis)) {
                    JOptionPane.showMessageDialog(null, "Ya existe un diagnóstico con los mismos datos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    UtilityMethods.addNewDiagnosis(diagnosis);
                }
            }
        });
        addDiagnosisPanel.add(addDiagnosisButton);
    }

    private void editInputs() {
        patientComboBox = new JComboBox();
        for (Patients patient : UtilityMethods.getPatientsList()) {
            patientComboBox.addItem(patient.getFirstLastName() + " " + patient.getSecondLastName() + ", " + patient.getName() + " - " + patient.getDni());
        }
        patientComboBox.setBounds(316, 50, 300, 20);
        addDiagnosisPanel.add(patientComboBox);

        diseaseComboBox = new JComboBox();
        for (Diseases disease : UtilityMethods.getDiseaseList()) {
            diseaseComboBox.addItem(disease.getName() + " - " + disease.getId());
        }
        diseaseComboBox.setBounds(316, 100, 300, 20);
        addDiagnosisPanel.add(diseaseComboBox);
    }

    private void addTabbedPanels() {
        contentPane.addTab("Mi información", null, mainInfoPanel, null);
        contentPane.addTab("Mis casos activos", null, activeDiagnosisPanel, null);
        contentPane.addTab("Mis altas", null, closedDiagnosisPanel, null);
        contentPane.addTab("Añadir caso", null, addDiagnosisPanel, null);
        contentPane.addTab("Todos los pacientes", null, allPatientsPanel, null);
    }

    private void editTables() {
        editTableActive();
        editTableClosed();
        editTablePatients();
    }

    private void editTablePatients() {
        String[] columsPatient = {"DNI", "Nombre", "Primer apellido", "Segundo apellido", "Localidad", "Teléfono"};
        String[][] dataPatient = UtilityMethods.buildTable(UtilityMethods.patientsInformation());

        tablePatients = new JTable(dataPatient, columsPatient);
        tablePatients.setBounds(10, 11, 945, 641);

        JScrollPane scrollTablePatients = new JScrollPane(tablePatients);
        scrollTablePatients.setBounds(10, 11, 945, 641);
        allPatientsPanel.add(scrollTablePatients);
    }

    private void editTableClosed() {
        String[] columnsDiagnosis = {"DNI", "Paciente", "Enfermedad", "Teléfono", "Fecha"};
        String[][] dataClosed = UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(true));

        tableClosed = new JTable(dataClosed, columnsDiagnosis);
        tableClosed.setBounds(10, 11, 945, 641);

        JScrollPane scrollTableClosed = new JScrollPane(tableClosed);
        scrollTableClosed.setBounds(10, 11, 945, 641);
        closedDiagnosisPanel.add(scrollTableClosed);
    }

    private void editTableActive() {
        String[] columnsDiagnosis = {"DNI", "Paciente", "Enfermedad", "Teléfono", "Fecha"};
        String[][] dataActive = UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(false));

        tableActive = new JTable(dataActive, columnsDiagnosis);
        tableActive.setBounds(10, 11, 945, 641);

        JScrollPane scrollTableActive = new JScrollPane(tableActive);
        scrollTableActive.setBounds(10, 11, 945, 641);
        activeDiagnosisPanel.add(scrollTableActive);
    }

    private void editLabels() {
        nameLabel = new JLabel("¡Buenos días, nombre!");
        nameLabel.setBounds(210, 65, 259, 14);
        mainInfoPanel.add(nameLabel);

        dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(210, 133, 97, 14);
        mainInfoPanel.add(dniLabel);

        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(210, 158, 97, 14);
        mainInfoPanel.add(passwordLabel);

        specialityLabel = new JLabel("Especialidad:");
        specialityLabel.setBounds(210, 183, 97, 14);
        mainInfoPanel.add(specialityLabel);

        patientLabel = new JLabel("Paciente:");
        patientLabel.setBounds(145, 50, 161, 20);
        addDiagnosisPanel.add(patientLabel);

        diseaseLabel = new JLabel("Enfermedad:");
        diseaseLabel.setBounds(145, 100, 161, 20);
        addDiagnosisPanel.add(diseaseLabel);
    }

    private void editPanels() {
        contentPane = new JTabbedPane();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        mainInfoPanel = new JPanel();
        mainInfoPanel.setLayout(null);

        activeDiagnosisPanel = new JPanel();
        activeDiagnosisPanel.setLayout(null);

        closedDiagnosisPanel = new JPanel();
        closedDiagnosisPanel.setLayout(null);

        addDiagnosisPanel = new JPanel();
        addDiagnosisPanel.setLayout(null);

        allPatientsPanel = new JPanel();
        allPatientsPanel.setLayout(null);
    }

    private void editFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 996, 740);
        this.setVisible(true);
    }
}