package UI;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.io.File;
import java.sql.SQLException;

public class WelcomeFrame extends JFrame implements HospitalUI {
	public static WelcomeFrame welcomeFrame;

	private JPanel cardPanel;
	private WelcomePanel welcomePanel;
	private LogInPanel logInPanel;
	private SignUpPanel signUpPanel;

	//Identifiers
	final static String WELCOMEPANEL = "Hospital";
	final static String LOGINPANEL = "Iniciar sesión";
	final static String SIGNUPPANEL = "Registrarse";

	public WelcomeFrame() {
		welcomeFrame = this;

		editFrame();

		welcomePanel = new WelcomePanel();
		logInPanel = new LogInPanel();
		signUpPanel = new SignUpPanel();

		editPanel();
		
		//Add Panels on window
		cardPanel.add(welcomePanel, WELCOMEPANEL);
		cardPanel.add(logInPanel, LOGINPANEL);
		cardPanel.add(signUpPanel, SIGNUPPANEL);

		editCardButtons();
	}

	private void editCardButtons() {
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
		logInPanel.backButton.addActionListener(goBack);
		signUpPanel.backButton.addActionListener(goBack);
	}

	private void editPanel() {
		cardPanel = new JPanel();

		add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
	}

	private void editFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(welcomeFrameWidth, welcomeFrameHeight));
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void closeFrame(){
		this.dispose();
	}

	public static void main(String[] args) {
		new WelcomeFrame();
		UtilityMethods.checkDiseases();
    }
}