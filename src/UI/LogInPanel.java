package UI;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton btnNewButton = new JButton("Back");

	public LogInPanel() {
		setLayout(null);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(71, 51, 89, 23);
		add(btnNewButton);

	}

}
