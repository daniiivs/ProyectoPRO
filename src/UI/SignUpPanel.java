package UI;

import Entities.Speciality;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpPanel extends JPanel implements HospitalUI {
    private JLabel dniLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel nameLabel;
    private JLabel firstLastNameLabel;
    private JLabel secondLastNameLabel;
    private JLabel specialityLabel;

    private JTextField dniField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;
    private JTextField firstLastNameField;
    private JTextField secondLastNameField;
    private JComboBox specialityComboBox;

    private JButton signUpButton;
    protected JButton backButton;

    public SignUpPanel() {
        setLayout(null);
        editLabels();
        editInputs();
        editButtons();
    }

    private void editButtons() {
        signUpButton = new JButton("Registrarse");
        signUpButton.setBounds((welcomeFrameWidth / 2 - 75) - 85, 450, 150, 40);
        signUpButton.setFont(buttonFont);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!UtilityMethods.checkDni(dniField.getText().toUpperCase())) {
                    dniField.setText("");
                    JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Por favor, introduzca un DNI válido", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Por favor, introduzca una contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!UtilityMethods.checkMatch(String.valueOf(passwordField.getPassword()), String.valueOf(confirmPasswordField.getPassword()))) {
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (nameField.getText().isEmpty() || firstLastNameField.getText().isEmpty() || secondLastNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Please type your personal information", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (UtilityMethods.dniIsRegistered(dniField.getText().toUpperCase())) {
                    dniField.setText("");
                    JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "El DNI que ha introducido ya se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    UtilityMethods.addNewDoctor(dniField.getText().toUpperCase(), nameField.getText().toUpperCase(), String.valueOf(passwordField.getPassword()), firstLastNameField.getText().toUpperCase(), secondLastNameField.getText().toUpperCase(), UtilityMethods.splitSpeciality(String.valueOf(specialityComboBox.getSelectedItem())));
                    resetInputs();
                    JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Se ha registrado exitosamente", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(signUpButton);

        backButton = new JButton("Atrás");
        backButton.setBounds((welcomeFrameWidth / 2 - 75) + 85, 450, 150, 40);
        backButton.setFont(buttonFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetInputs();
            }
        });
        add(backButton);
    }

    private void resetInputs() {
        dniField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        nameField.setText("");
        firstLastNameField.setText("");
        secondLastNameField.setText("");
        specialityComboBox.setSelectedIndex(0);
    }

    private void editInputs() {
        dniField = new JTextField();
        dniField.setBounds(330, 50, 250, 30);
        add(dniField);
        dniField.setFont(labelInputFont);
        dniField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(330, 100, 250, 30);
        passwordField.setFont(labelInputFont);
        add(passwordField);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(330, 150, 250, 30);
        confirmPasswordField.setFont(labelInputFont);
        add(confirmPasswordField);

        nameField = new JTextField();
        nameField.setColumns(10);
        nameField.setBounds(330, 200, 250, 30);
        nameField.setFont(labelInputFont);
        add(nameField);

        firstLastNameField = new JTextField();
        firstLastNameField.setColumns(10);
        firstLastNameField.setBounds(330, 250, 250, 30);
        firstLastNameField.setFont(labelInputFont);
        add(firstLastNameField);

        secondLastNameField = new JTextField();
        secondLastNameField.setColumns(10);
        secondLastNameField.setBounds(330, 300, 250, 30);
        secondLastNameField.setFont(labelInputFont);
        add(secondLastNameField);

        specialityComboBox = new JComboBox();
        for (Speciality speciality : UtilityMethods.getSpecialityList()) {
            specialityComboBox.addItem(speciality.getId() + " - " + speciality.getName());
        }
        specialityComboBox.setBounds(330, 350, 250, 30);
        specialityComboBox.setFont(labelInputFont);
        specialityComboBox.setBackground(Color.white);
        add(specialityComboBox);
    }

    private void editLabels() {
        dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(110, 50, 200, 30);
        dniLabel.setFont(labelInputFont);
        add(dniLabel);

        passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(110, 100, 200, 30);
        passwordLabel.setFont(labelInputFont);
        add(passwordLabel);

        confirmPasswordLabel = new JLabel("Confirmar contraseña:");
        confirmPasswordLabel.setBounds(110, 150, 200, 30);
        confirmPasswordLabel.setFont(labelInputFont);
        add(confirmPasswordLabel);

        nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(110, 200, 200, 30);
        nameLabel.setFont(labelInputFont);
        add(nameLabel);

        firstLastNameLabel = new JLabel("Primer apellido:");
        firstLastNameLabel.setBounds(110, 250, 200, 30);
        firstLastNameLabel.setFont(labelInputFont);
        add(firstLastNameLabel);

        secondLastNameLabel = new JLabel("Segundo apellido:");
        secondLastNameLabel.setBounds(110, 300, 200, 30);
        secondLastNameLabel.setFont(labelInputFont);
        add(secondLastNameLabel);

        specialityLabel = new JLabel("Especialidad:");
        specialityLabel.setBounds(110, 350, 200, 30);
        specialityLabel.setFont(labelInputFont);
        add(specialityLabel);
    }
}