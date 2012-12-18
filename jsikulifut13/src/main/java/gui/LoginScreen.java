package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LoginScreen {
	
	JFrame frame;
	JPanel panel;
	JPanel panelCredentials;
	JButton buttonLogin;
	
	JLabel emailLabel;
	JTextField emailText;

	JLabel passwordLabel;
	JPasswordField passwordText;
	
	public LoginScreen(final Runnable loginCommand) {
		frame = new JFrame();
		panel = new JPanel(new GridLayout(2, 1));
		panelCredentials = new JPanel(new GridLayout(2, 2));
		//panel2 = new JPanel();
	

		emailLabel = new JLabel("Email: ");
		emailText = new JTextField("Enter email address here...    ");
		passwordLabel = new JLabel("Password: ");
		passwordText = new JPasswordField("");	
		buttonLogin = new JButton("Login");
		
		emailText.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				emailText.setText("");
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new Thread(loginCommand).start();
			}
		});
		
		panelCredentials.add(emailLabel);
		panelCredentials.add(emailText);	
		panelCredentials.add(passwordLabel);
		panelCredentials.add(passwordText);
		
		panel.add(panelCredentials);
		panel.add(buttonLogin);

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	
	public String getEmailText() {
		return emailText.getText();
	}

	public char[] getPasswordText() {
		return passwordText.getPassword();
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(true);
	}
	
	public void launch() {	
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
