package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JButton;

public class WelcomePanel extends JPanel {

	JLabel lblNewLabel = new JLabel("HOSPITAL");
	JButton btnLogIn = new JButton("Log In");
	JButton btnSignUp = new JButton("Sign Up");
	
	private static final long serialVersionUID = 1L;


	public WelcomePanel() {
		setLayout(null);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(350, 156, 106, 14);
		add(lblNewLabel);
		
		btnLogIn.setBounds(187, 259, 89, 23);
		add(btnLogIn);
		
		btnSignUp.setBounds(536, 259, 89, 23);
		add(btnSignUp);

	}

}
