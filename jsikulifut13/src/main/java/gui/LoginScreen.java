package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LoginScreen {
	
	JFrame frame;
	JPanel panel;
	JPanel panelCredentials;
	JPanel panel2;
	JButton buttonLogin;
	JButton button2;
	JLabel emailLabel;
	JTextField emailText;
	JLabel passwordLabel;
	JTextField passwordText;
	
	public LoginScreen() {
		frame = new JFrame();
		panel = new JPanel(new GridLayout(2, 1));
		panelCredentials = new JPanel(new GridLayout(2, 2));
		//panel2 = new JPanel();
		buttonLogin = new JButton("Login");
		//button2 = new JButton("MOOB");
		emailLabel = new JLabel("Email: ");
		emailText = new JTextField("Enter email address here...    ");
		passwordLabel = new JLabel("Password: ");
		passwordText = new JTextField("");
		
		//panel2.add(button2);
		panelCredentials.add(emailLabel);
		panelCredentials.add(emailText);
		
		panelCredentials.add(passwordLabel);
		panelCredentials.add(passwordText);
		
		panel.add(panelCredentials);
		panel.add(buttonLogin);
		//panel.add(panel2);
		frame.add(panel);
		//frame.add
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(true);
	}
	
	public static void main(String[] args){	
		LoginScreen.launch();
	}
	
	public static void launch() {	
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		LoginScreen displayer = new LoginScreen();
		
		displayer.setVisible(true);
	}
}
