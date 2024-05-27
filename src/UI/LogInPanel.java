package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that creates the log in panel
 */
public class LogInPanel extends JPanel implements HospitalUI {
	private JLabel dniLabel;
	private JLabel passwordLabel;

	private JTextField dniField;
	private JPasswordField passwordField;

	private JButton logInButton;
	protected JButton backButton;

	/**
	 * Constructor for LogInPanel
	 */
	public LogInPanel() {
		setLayout(null);
		editLabels();
		editInputs();
		editButtons();
	}

	/**
	 * Method for editing all buttons' properties, and adding action listeners
	 */
	private void editButtons() {
		logInButton = new JButton("Iniciar sesión");
		logInButton.setBounds((welcomeFrameWidth / 2 - 75) - 85, 300, 150, 40);
		logInButton.setFont(buttonFont);
		logInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!UtilityMethods.dniIsRegistered(dniField.getText().toUpperCase())) { // Checks if the DNI is registered
					dniField.setText("");
					JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "El DNI que ha introducido no se encuentra registrado", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!UtilityMethods.passwordMatching(String.valueOf(passwordField.getPassword()), dniField.getText().toUpperCase())) { // Checks if password matches the one in the database
					passwordField.setText("");
					JOptionPane.showMessageDialog(WelcomeFrame.welcomeFrame, "Contraseña incorrecta, por favor inténtelo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
				} else { // Calls logIn() to open new window
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

	/**
	 * Method for clearing all inputs
	 */
	private void resetInputs() {
		dniField.setText("");
		passwordField.setText("");
	}

	/**
	 * Method for editing all inputs
	 */
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

	/**
	 * Method for editing all labels
	 */
	private void editLabels() {
		dniLabel = new JLabel("DNI:", userIcon, SwingConstants.LEFT);
		dniLabel.setBounds(160, 150, 200, 30);
		dniLabel.setFont(labelInputFont);
		add(dniLabel);

		passwordLabel = new JLabel("Contraseña:", keyIcon, SwingConstants.LEFT);
		passwordLabel.setBounds(160, 200, 200, 30);
		passwordLabel.setFont(labelInputFont);
		add(passwordLabel);
	}
}