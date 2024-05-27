package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Class that creates the welcome panel
 */
public class WelcomePanel extends JPanel implements HospitalUI {
	JLabel lblNewLabel = new JLabel("VITALCLINIC", hospitalIcon, JLabel.CENTER);
	JButton btnLogIn = new JButton("Iniciar sesión");
	JButton btnSignUp = new JButton("Registrarse");

	/**
	 * Constructor for WelcomePanel
	 */
	public WelcomePanel() {
		setLayout(null);
		setBackground(Color.white);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(titleFont);
		lblNewLabel.setBounds(0, 156, welcomeFrameWidth, 100);
		add(lblNewLabel);
		
		btnLogIn.setBounds((welcomeFrameWidth / 2 - 75) - 85, 300, 150, 40);
		btnLogIn.setFont(buttonFont);
		add(btnLogIn);
		
		btnSignUp.setBounds((welcomeFrameWidth / 2 - 75) + 85, 300, 150, 40);
		btnSignUp.setFont(buttonFont);
		add(btnSignUp);
	}
}