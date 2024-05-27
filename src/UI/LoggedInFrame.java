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

/**
 * Class for creating the logged in frame
 */
public class LoggedInFrame extends JFrame implements HospitalUI {
    public static LoggedInFrame loggedInFrame; // Variable that saves the created frame, making it reachable and usable by other classes

    private Doctors loggedUser; // The doctor that logged in

    private JTabbedPane contentPane; // The main panel that contains all tabs

    // All used panels
    private JPanel mainInfoPanel;
    private JPanel activeDiagnosisPanel;
    private JPanel closedDiagnosisPanel;
    private JPanel addDiagnosisPanel;
    private JPanel allPatientsPanel;

    // All used labels
    private JLabel nameLabel;
    private JLabel dniLabel;
    private JLabel specialityLabel;
    private JLabel patientLabel;
    private JLabel diseaseLabel;

    // All used comboboxes
    private JComboBox patientComboBox;
    private JComboBox diseaseComboBox;

    // All used tables
    private JTable tableActive;
    private JTable tableClosed;
    private JTable tablePatients;

    // Models for the diagnosis tables
    private DefaultTableModel modelActive;
    private DefaultTableModel modelClosed;

    // Headers for the diagnosis table
    String[] columnsDiagnosis = {"DNI", "Paciente", "Enfermedad", "Teléfono", "Fecha"};

    // All used buttons
    private JButton addDiagnosisButton;
    private JButton logOutButton;

    /**
     * Constructor for LoggedInFrame
     * @param user the doctor that logged in
     */
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

    /**
     * Method for editing all buttons, setting their properties and action listeners
     */
    private void editButtons() {
        addDiagnosisButton = new JButton("Registrar Caso");
        addDiagnosisButton.setBounds(600 - 100, 300, 200, 40);
        addDiagnosisButton.setFont(buttonFont);
        addDiagnosisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Diagnosis diagnosis = new Diagnosis(UtilityMethods.splitPatient(String.valueOf(patientComboBox.getSelectedItem())), loggedUser, UtilityMethods.splitDisease(String.valueOf(diseaseComboBox.getSelectedItem())));
                if (UtilityMethods.diagnosisExists(diagnosis)) { // Checks if the diagnosis is already registered
                    JOptionPane.showMessageDialog(loggedInFrame, "Ya existe un diagnóstico con los mismos datos", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    UtilityMethods.addNewDiagnosis(diagnosis); // Adds the diagnosis to the database
                    updateTableModels(); // Updates table
                    JOptionPane.showMessageDialog(loggedInFrame, "Se ha añadido el nuevo caso", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addDiagnosisPanel.add(addDiagnosisButton);

        logOutButton = new JButton("Cerrar sesión");
        logOutButton.setBounds(600 - 75, 550, 150, 40);
        logOutButton.setFont(buttonFont);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(loggedInFrame, "¿Seguro que quieres salir?", "exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    loggedInFrame.dispose(); // Closes the frame
                    loggedUser = null; // Resets the loggedUser
                    new WelcomeFrame(); // Creates a new WelcomeFrame
                }
            }
        });
        mainInfoPanel.add(logOutButton);
    }

    /**
     * Method for updating tables, getting their content from the database once there's been a change
     */
    private void updateTableModels() {
        modelActive.setDataVector(UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(false, loggedUser)), columnsDiagnosis);
        modelClosed.setDataVector(UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(true, loggedUser)), columnsDiagnosis);
    }

    /**
     * Method for editing all inputs
     */
    private void editInputs() {
        patientComboBox = new JComboBox();
        for (Patients patient : UtilityMethods.getPatientsList()) { // Adds each patient from the database along with their DNI
            patientComboBox.addItem(patient.getFirstLastName() + " " + patient.getSecondLastName() + ", " + patient.getName() + " - " + patient.getDni());
        }
        patientComboBox.setBounds(475, 125, 450, 40);
        patientComboBox.setFont(addPatientFont);
        addDiagnosisPanel.add(patientComboBox);

        diseaseComboBox = new JComboBox(); // Adds each disease from the database along with its ID
        for (Diseases disease : UtilityMethods.getDiseaseList()) {
            diseaseComboBox.addItem(disease.getName() + " - " + disease.getId());
        }
        diseaseComboBox.setBounds(475, 200, 450, 40);
        diseaseComboBox.setFont(addPatientFont);
        addDiagnosisPanel.add(diseaseComboBox);
    }

    /**
     * Method for adding the different tabs (panel) to the main panel
     */
    private void addTabbedPanels() {
        contentPane.addTab("Mi información", myInfoIcon, mainInfoPanel, null);
        contentPane.addTab("Mis casos activos", activeCasesIcon, activeDiagnosisPanel, null);
        contentPane.addTab("Mis altas", closedCasesIcon, closedDiagnosisPanel, null);
        contentPane.addTab("Añadir caso", newCaseIcon, addDiagnosisPanel, null);
        contentPane.addTab("Todos los pacientes", allPatientsIcon, allPatientsPanel, null);
        contentPane.setFont(tabFont);
    }

    /**
     * Method for editing the different table, each one having its own method
     */
    private void editTables() {
        editTableActive();
        editTableClosed();
        editTablePatients();
    }

    /**
     * Method for editing the patients table
     */
    private void editTablePatients() {
        String[] columsPatient = {"DNI", "Primer apellido", "Segundo apellido", "Nombre", "Localidad", "Teléfono"};
        String[][] dataPatient = UtilityMethods.buildTable(UtilityMethods.patientsInformation());

        // This prevents the user from editing the table when clicking it
        tablePatients = new JTable(dataPatient, columsPatient) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablePatients.getTableHeader().setReorderingAllowed(false); // This prevents the user from reordering the different columns of teh table
        tablePatients.setRowHeight(25);
        tablePatients.getTableHeader().setFont(tableHeaderFont);
        tablePatients.setFont(tableFont);

        JScrollPane scrollTablePatients = new JScrollPane(tablePatients); // This adds a scroll bar to the table for when there are too many records
        scrollTablePatients.setBounds(5, 10, this.getWidth() - 40, this.getHeight() - 105);
        allPatientsPanel.add(scrollTablePatients, CENTER_ALIGNMENT);
    }

    /**
     * Method for editing the closed diagnosis table
     */
    private void editTableClosed() {
        String[][] dataClosed = UtilityMethods.buildTable(UtilityMethods.diagnosisInformation(true, loggedUser));

        tableClosed = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelClosed = new DefaultTableModel(); // This is the model for the data that the table contains
        modelClosed.setDataVector(dataClosed, columnsDiagnosis); // Sets the data and the names for each column
        tableClosed.setModel(modelClosed); // Sets model to the table
        tableClosed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Only triggers if the table has been clicked twice
                    JTable clickedTable = (JTable) e.getSource(); // Variable to save the table that has been clicked
                    int row = clickedTable.getSelectedRow(); // Gets the row that was clicked
                    if (JOptionPane.showConfirmDialog(loggedInFrame, "¿Desea eliminar el caso seleccionado?", "option", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        String[] diagnosisInfo = getDiagnosisInfo(row, clickedTable); // Gets the information from the row
                        UtilityMethods.deleteDiagnosis(loggedUser, diagnosisInfo[0], diagnosisInfo[1], diagnosisInfo[2]);
                        updateTableModels(); // Updates the table
                        JOptionPane.showMessageDialog(loggedInFrame, "El caso se ha eliminado", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        tableClosed.getTableHeader().setReorderingAllowed(false);
        tableClosed.setRowHeight(25);
        tableClosed.getTableHeader().setFont(tableHeaderFont);
        tableClosed.setFont(tableFont);

        JScrollPane scrollTableClosed = new JScrollPane(tableClosed);
        scrollTableClosed.setBounds(5, 10, this.getWidth() - 40, this.getHeight() - 105);
        closedDiagnosisPanel.add(scrollTableClosed);
    }

    /**
     * Method for editing the active diagnosis table
     */
    private void editTableActive() {
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
                    if (JOptionPane.showConfirmDialog(loggedInFrame, "¿Desea dar el alta al caso seleccionado?", "option", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        String[] diagnosisInfo = getDiagnosisInfo(row, clickedTable);
                        UtilityMethods.closeDiagnosis(loggedUser, diagnosisInfo[0], diagnosisInfo[1], diagnosisInfo[2]);
                        updateTableModels();
                        JOptionPane.showMessageDialog(loggedInFrame, "El caso se ha cerrado", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        tableActive.getTableHeader().setReorderingAllowed(false);
        tableActive.setRowHeight(25);
        tableActive.getTableHeader().setFont(tableHeaderFont);
        tableActive.setFont(tableFont);

        JScrollPane scrollTableActive = new JScrollPane(tableActive);
        scrollTableActive.setBounds(5, 10, this.getWidth() - 40, this.getHeight() - 105);
        activeDiagnosisPanel.add(scrollTableActive, CENTER_ALIGNMENT);
    }

    /**
     * Method for getting the information contained in a clicked row from a table
     * @param row row that was clicked
     * @param table table that was clicked
     * @return an array containing the value of the patient's DNI, disease name and date
     */
    private String[] getDiagnosisInfo(int row, JTable table) {
        String[] diagnosisInfo = new String[3];
        diagnosisInfo[0] = (String) table.getValueAt(row, 0);
        diagnosisInfo[1] = (String) table.getValueAt(row, 2);
        diagnosisInfo[2] = (String) table.getValueAt(row, 4);
        return diagnosisInfo;
    }

    /**
     * Method for editing all labels
     */
    private void editLabels() {
        nameLabel = new JLabel(loggedUser.getName() + " " + loggedUser.getFirstLastName() + " " + loggedUser.getSecondLastName(), doctorIcon, SwingConstants.LEFT);
        nameLabel.setBounds(100, 50, 1200, 70);
        nameLabel.setFont(nameFont);
        mainInfoPanel.add(nameLabel);

        dniLabel = new JLabel("DNI: " + loggedUser.getDni());
        dniLabel.setBounds(100, 150, 600, 50);
        dniLabel.setFont(informationFont);
        mainInfoPanel.add(dniLabel);

        specialityLabel = new JLabel("Especialidad: " + loggedUser.getSpeciality().getName());
        specialityLabel.setBounds(100, 200, 600, 50);
        specialityLabel.setFont(informationFont);
        mainInfoPanel.add(specialityLabel);

        patientLabel = new JLabel("Paciente:", userIcon, SwingConstants.LEFT);
        patientLabel.setBounds(300, 125, 300, 40);
        patientLabel.setFont(addPatientFont);
        addDiagnosisPanel.add(patientLabel);

        diseaseLabel = new JLabel("Enfermedad:", diseaseIcon, SwingConstants.LEFT);
        diseaseLabel.setBounds(300, 200, 300, 40);
        diseaseLabel.setFont(addPatientFont);
        addDiagnosisPanel.add(diseaseLabel);
    }

    /**
     * Method for editing all panels
     */
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

    /**
     * Method for editing the frame
     */
    private void editFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
}