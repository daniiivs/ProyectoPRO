package UI;

import Entities.Doctors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanel extends JPanel {
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
		logInButton = new JButton("Log In");
		logInButton.setBounds(119, 200, 89, 23);
		logInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!UtilityMethods.dniIsRegistered(dniField.getText())) {
					dniField.setText("");
					JOptionPane.showMessageDialog(null, "The DNI you typed is not registered", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!UtilityMethods.passwordNotMatching(String.valueOf(passwordField.getPassword()), dniField.getText())) {
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "Incorrect password. Please try again", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					UtilityMethods.logIn(dniField.getText());
					resetInputs();
				}
			}
		});
		add(logInButton);

		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetInputs();
			}
		});
		backButton.setBounds(375, 200, 89, 23);
		add(backButton);
	}

	private void resetInputs() {
		dniField.setText("");
		passwordField.setText("");
	}

	private void editInputs() {
		dniField = new JTextField();
		dniField.setBounds(316, 50, 200, 20);
		add(dniField);
		dniField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(316, 100, 200, 20);
		add(passwordField);
	}

	private void editLabels() {
		dniLabel = new JLabel("DNI:");
		dniLabel.setBounds(145, 50, 161, 20);
		add(dniLabel);

		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(145, 100, 161, 20);
		add(passwordLabel);
	}
}