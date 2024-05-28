package UI;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.CardLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for launching the app. It shows the first frame, which lets the user sign up and log in
 */
public class WelcomeFrame extends JFrame implements HospitalUI {
	public static WelcomeFrame welcomeFrame; // Variable for the frame that gets opened once the app launches, so that every class can access it

	// Panels
	private JPanel cardPanel; // Main panel that contains the other three
	private WelcomePanel welcomePanel;
	private LogInPanel logInPanel;
	private SignUpPanel signUpPanel;

	// Identifiers for each panel
	final static String WELCOMEPANEL = "Hospital";
	final static String LOGINPANEL = "Iniciar sesión";
	final static String SIGNUPPANEL = "Registrarse";

	// Constructor for the WelcomeFrame
	public WelcomeFrame() {
		welcomeFrame = this;

		editFrame();
		editPanel();
		editCardButtons();
	}

	/**
	 * Method for adding the different action listeners to the buttons
	 */
	private void editCardButtons() {
		welcomePanel.btnLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout) cardPanel.getLayout(); // Gets the layout of the main panel
				layout.show(cardPanel, LOGINPANEL); // Changes it to the specified panel
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

	/**
	 * Method for setting up the different panels
	 */
	private void editPanel() {
		welcomePanel = new WelcomePanel();
		logInPanel = new LogInPanel();
		signUpPanel = new SignUpPanel();

		cardPanel = new JPanel();
		add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));

		//Add Panels on window
		cardPanel.add(welcomePanel, WELCOMEPANEL);
		cardPanel.add(logInPanel, LOGINPANEL);
		cardPanel.add(signUpPanel, SIGNUPPANEL);
	}

	/**
	 * Method for setting up the frame
	 */
	private void editFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(welcomeFrameWidth, welcomeFrameHeight));
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * Method to close de frame
	 */
	public void closeFrame(){
		this.dispose();
	}

	/**
	 * Launcher for the app
	 * @param args none
	 */
	public static void main(String[] args) {
		getConnectionInfo();
		new WelcomeFrame();
		UtilityMethods.checkDiseases(); // Checks if there are any diseases registered in the database
    }

	/**
	 * Method that checks if the file that contains the information to connect to the database exists. If it doesn't, it generates it
	 */
	private static void getConnectionInfo() {
		File databaseInfo = new File("./src/DatabaseConnection/DatabaseInfo.txt");
		if (!databaseInfo.exists()) {
			try {
				if (databaseInfo.createNewFile()){
					BufferedWriter bw = new BufferedWriter(new FileWriter(databaseInfo, true));
					bw.write("ip$localhost\n");
					bw.write("port$3306\n");
					bw.write("db$hospitalPRO\n");
					bw.write("user$root\n");
					bw.write("password$none");
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}