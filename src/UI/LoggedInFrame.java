package UI;

import Entities.Diagnosis;
import Entities.Diseases;
import Entities.Doctors;
import Entities.Patients;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class LoggedInFrame extends JFrame {
    public static LoggedInFrame loggedInFrame;

    private Doctors loggedUser;

    private JTabbedPane contentPane;

    private JPanel mainInfoPanel;
    private JPanel activeDiagnosisPanel;
    private JPanel closedDiagnosisPanel;
    private JPanel addDiagnosisPanel;
    private JPanel allPatientsPanel;

    private JLabel nameLabel;
    private JLabel dniLabel;
    private JLabel specialityLabel;

    private JLabel patientLabel;
    private JLabel diseaseLabel;

    private JComboBox patientComboBox;
    private JComboBox diseaseComboBox;

    private JTable tableActive;
    private JTable tableClosed;
    private JTable tablePatients;

    private DefaultTableModel modelActive;
    private DefaultTableModel modelClosed;

    String[] columnsDiagnosis = {"DNI", "Paciente", "Enfermedad", "Teléfono", "Fecha"};

    private JButton addDiagnosisButton;
    private JButton logOutButton;

    public LoggedInFrame(Doctors user) {
        this.loggedUser = user;

        editFrame();
        editPanels();
        editTables();
        editLabels();
        editInputs();
        editButtons();
        addTabbedPanels();

        loggedInFrame = this;
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
                    updateTableModels();
                    JOptionPane.showMessageDialog(null, "Se ha añadido el nuevo caso", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addDiagnosisPanel.add(addDiagnosisButton);

        logOutButton = new JButton("Cerrar sesión");
        logOutButton.setBounds(238, 232, 119, 23);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres salir?", "exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    loggedInFrame.dispose();
                    loggedUser = null;
                    new WelcomeFrame();
                }
            }
        });
        mainInfoPanel.add(logOutButton);
    }

    private void updateTableModels() {
        modelActive.setDataVector(UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(false, loggedUser)), columnsDiagnosis);
        modelClosed.setDataVector(UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(true, loggedUser)), columnsDiagnosis);
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
        String[] columsPatient = {"DNI", "Primer apellido", "Segundo apellido", "Nombre", "Localidad", "Teléfono"};
        String[][] dataPatient = UtilityMethods.buildTable(UtilityMethods.patientsInformation());

        tablePatients = new JTable(dataPatient, columsPatient) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablePatients.setBounds(10, 11, 945, 641);

        JScrollPane scrollTablePatients = new JScrollPane(tablePatients);
        scrollTablePatients.setBounds(10, 11, 945, 641);
        allPatientsPanel.add(scrollTablePatients);
    }

    private void editTableClosed() {
        String[] columnsDiagnosis = {"DNI", "Paciente", "Enfermedad", "Teléfono", "Fecha"};
        String[][] dataClosed = UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(true, loggedUser));

        tableClosed = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelClosed = new DefaultTableModel();
        modelClosed.setDataVector(dataClosed, columnsDiagnosis);
        tableClosed.setModel(modelClosed);
        tableClosed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable clickedTable = (JTable) e.getSource();
                    int row = clickedTable.getSelectedRow();
                    if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el caso seleccionado?", "option", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        String[] diagnosisInfo = getDiagnosisInfo(row, clickedTable);
                        UtilityMethods.deleteDiagnosis(loggedUser, diagnosisInfo[0], diagnosisInfo[1], diagnosisInfo[2]);
                        updateTableModels();
                        JOptionPane.showMessageDialog(null, "El caso se ha eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        tableClosed.setBounds(10, 11, 945, 641);

        JScrollPane scrollTableClosed = new JScrollPane(tableClosed);
        scrollTableClosed.setBounds(10, 11, 945, 641);
        closedDiagnosisPanel.add(scrollTableClosed);
    }

    private void editTableActive() {
        String[] columnsDiagnosis = {"DNI", "Paciente", "Enfermedad", "Teléfono", "Fecha"};
        String[][] dataActive = UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(false, loggedUser));

        tableActive = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelActive = new DefaultTableModel();
        modelActive.setDataVector(dataActive, columnsDiagnosis);
        tableActive.setModel(modelActive);
        tableActive.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable clickedTable = (JTable) e.getSource();
                    int row = clickedTable.getSelectedRow();
                    if (JOptionPane.showConfirmDialog(null, "¿Desea dar el alta al caso seleccionado?", "option", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        String[] diagnosisInfo = getDiagnosisInfo(row, clickedTable);
                        UtilityMethods.closeDiagnosis(loggedUser, diagnosisInfo[0], diagnosisInfo[1], diagnosisInfo[2]);
                        updateTableModels();
                        JOptionPane.showMessageDialog(null, "El caso se ha cerrado", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        tableActive.setBounds(10, 11, 945, 641);

        JScrollPane scrollTableActive = new JScrollPane(tableActive);
        scrollTableActive.setBounds(10, 11, 945, 641);
        activeDiagnosisPanel.add(scrollTableActive);
    }

    private String[] getDiagnosisInfo(int row, JTable table) {
        String[] diagnosisInfo = new String[3];
        diagnosisInfo[0] = (String) table.getValueAt(row, 0);
        diagnosisInfo[1] = (String) table.getValueAt(row, 2);
        diagnosisInfo[2] = (String) table.getValueAt(row, 4);
        return diagnosisInfo;
    }

    private void editLabels() {
        nameLabel = new JLabel("¡Buenos días, " + loggedUser.getName() + " " + loggedUser.getFirstLastName() + " " + loggedUser.getSecondLastName() + "!");
        nameLabel.setBounds(210, 65, 259, 14);
        mainInfoPanel.add(nameLabel);

        dniLabel = new JLabel("DNI: " + loggedUser.getDni());
        dniLabel.setBounds(210, 133, 200, 14);
        mainInfoPanel.add(dniLabel);

        specialityLabel = new JLabel("Especialidad: " + loggedUser.getSpeciality().getName());
        specialityLabel.setBounds(210, 183, 200, 14);
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
        add(contentPane);

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