package UI;

import Entities.Doctors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends JPanel implements HospitalUI {
	private JLabel dniLabel;
	private JLabel passwordLabel;

	private JTextField dniField;
	private JPasswordField passwordField;

	private JButton logInButton;
	protected JButton backButton;

	public LogInPanel() {
		setLayout(null);
		editLabels();
		editInputs();
		editButtons();
	}

	private void editButtons() {
		logInButton = new JButton("Iniciar sesión");
		logInButton.setBounds((welcomeFrameWidth / 2 - 75) - 85, 300, 150, 40);
		logInButton.setFont(buttonFont);
		logInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!UtilityMethods.dniIsRegistered(dniField.getText().toUpperCase())) {
					dniField.setText("");
					JOptionPane.showMessageDialog(null, "The DNI you typed is not registered", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!UtilityMethods.passwordNotMatching(String.valueOf(passwordField.getPassword()), dniField.getText().toUpperCase())) {
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "Incorrect password. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					UtilityMethods.logIn(dniField.getText().toUpperCase());
				}
			}
		});
		add(logInButton);

		backButton = new JButton("Atrás");
		backButton.setBounds((welcomeFrameWidth / 2 - 75) + 85, 300, 150, 40);
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
	}

	private void editInputs() {
		dniField = new JTextField();
		dniField.setBounds(300, 150, 250, 30);
		dniField.setFont(labelInputFont);
		add(dniField);

		passwordField = new JPasswordField();
		passwordField.setBounds(300, 200, 250, 30);
		passwordField.setFont(labelInputFont);
		add(passwordField);
	}

	private void editLabels() {
		dniLabel = new JLabel("DNI:");
		dniLabel.setBounds(160, 150, 200, 30);
		dniLabel.setFont(labelInputFont);
		add(dniLabel);

		passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setBounds(160, 200, 200, 30);
		passwordLabel.setFont(labelInputFont);
		add(passwordLabel);
	}
}