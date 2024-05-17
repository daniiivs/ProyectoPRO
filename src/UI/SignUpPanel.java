package UI;

import Entities.Speciality;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpPanel extends JPanel {
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
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(119, 450, 89, 23);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!UtilityMethods.checkDni(dniField.getText())) {
                    dniField.setText("");
                    JOptionPane.showMessageDialog(null, "Please type a valid DNI", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please type a password", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!UtilityMethods.checkMatch(String.valueOf(passwordField.getPassword()), String.valueOf(confirmPasswordField.getPassword()))) {
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    JOptionPane.showMessageDialog(null, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (nameField.getText().isEmpty() || firstLastNameField.getText().isEmpty() || secondLastNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please type your personal information", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (UtilityMethods.DNIisRegistered(dniField.getText())) {
                    dniField.setText("");
                    JOptionPane.showMessageDialog(null, "The DNI you typed is already registered. Please type a different one", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    UtilityMethods.addNewDoctor(dniField.getText(), nameField.getText(), String.valueOf(passwordField.getPassword()), firstLastNameField.getText(), secondLastNameField.getText(), UtilityMethods.getSpecialityId(String.valueOf(specialityComboBox.getSelectedItem())));
                    resetInputs();
                    JOptionPane.showMessageDialog(null, "You've been registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(signUpButton);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetInputs();
            }
        });
        backButton.setBounds(375, 450, 89, 23);
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
        dniField.setBounds(316, 50, 200, 20);
        add(dniField);
        dniField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(316, 100, 200, 20);
        add(passwordField);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(316, 150, 200, 20);
        add(confirmPasswordField);

        nameField = new JTextField();
        nameField.setColumns(10);
        nameField.setBounds(316, 200, 200, 20);
        add(nameField);

        firstLastNameField = new JTextField();
        firstLastNameField.setColumns(10);
        firstLastNameField.setBounds(316, 250, 200, 20);
        add(firstLastNameField);

        secondLastNameField = new JTextField();
        secondLastNameField.setColumns(10);
        secondLastNameField.setBounds(316, 300, 200, 20);
        add(secondLastNameField);

        specialityComboBox = new JComboBox();
        for (Speciality speciality : UtilityMethods.getSpecialityList()) {
            specialityComboBox.addItem(speciality.getId() + " - " + speciality.getName());
        }
        specialityComboBox.setBounds(316, 350, 200, 20);
        add(specialityComboBox);
    }

    private void editLabels() {
        dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(145, 50, 161, 20);
        add(dniLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(145, 100, 161, 20);
        add(passwordLabel);

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(145, 150, 161, 20);
        add(confirmPasswordLabel);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(145, 200, 161, 20);
        add(nameLabel);

        firstLastNameLabel = new JLabel("First Last Name:");
        firstLastNameLabel.setBounds(145, 250, 161, 20);
        add(firstLastNameLabel);

        secondLastNameLabel = new JLabel("Second Last Name:");
        secondLastNameLabel.setBounds(145, 300, 161, 20);
        add(secondLastNameLabel);

        specialityLabel = new JLabel("Speciality:");
        specialityLabel.setBounds(145, 350, 161, 20);
        add(specialityLabel);
    }
}