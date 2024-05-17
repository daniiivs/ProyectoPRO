package UI;

import Model.Speciality;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.util.ArrayList;

public class WelcomeFrame extends JFrame {
	private JPanel cardPanel;
	private WelcomePanel welcomePanel;
	private LogInPanel logInPanel;
	private SignUpPanel signUpPanel;
	
	//Identifiers
	final static String WELCOMEPANEL = "Hospital";
	final static String LOGINPANEL = "Log In";
	final static String SIGNUPPANEL = "Sign Up";

	public WelcomeFrame() {
		welcomePanel = new WelcomePanel();
		logInPanel = new LogInPanel();
		signUpPanel = new SignUpPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(1000, 600));
		cardPanel = new JPanel();
		cardPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		
		//Add Panels on window
		cardPanel.add(welcomePanel, WELCOMEPANEL);
		cardPanel.add(logInPanel, LOGINPANEL);
		cardPanel.add(signUpPanel, SIGNUPPANEL);


		welcomePanel.btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) cardPanel.getLayout();
				layout.show(cardPanel, LOGINPANEL);
			}
		});
		
		welcomePanel.btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) cardPanel.getLayout();
				layout.show(cardPanel, SIGNUPPANEL);
			}
		});

		ActionListener goBack = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) cardPanel.getLayout();
				layout.show(cardPanel, WELCOMEPANEL);
			}
		};
		logInPanel.btnNewButton.addActionListener(goBack);
		signUpPanel.backButton.addActionListener(goBack);

		this.setVisible(true);
	}
}
