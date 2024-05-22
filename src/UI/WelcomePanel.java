package UI;

import javax.swing.*;

public class WelcomePanel extends JPanel implements HospitalUI {
	JLabel lblNewLabel = new JLabel("HOSPITAL");
	JButton btnLogIn = new JButton("Iniciar sesión");
	JButton btnSignUp = new JButton("Registrarse");

	public WelcomePanel() {
		setLayout(null);

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