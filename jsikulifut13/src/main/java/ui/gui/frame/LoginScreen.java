package ui.gui.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LoginScreen implements KeyListener {
	
	JFrame frame;
	JPanel panel;
	JPanel panelCredentials;
	JButton buttonLogin;
	
	JLabel emailLabel;
	JTextField emailText;

	JLabel passwordLabel;
	JPasswordField passwordText;
	
	final Runnable loginCommand;
	
	public LoginScreen(final Runnable loginCommand) {
		
		this.loginCommand = loginCommand;
		
		frame = new JFrame();
		panel = new JPanel(new GridLayout(2, 1));
		panelCredentials = new JPanel(new GridLayout(2, 2));
	
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
		
		passwordText.addKeyListener(this);
		buttonLogin.addKeyListener(this);
		
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
		
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == KeyEvent.VK_ENTER) { 
				new Thread(loginCommand).start();
			} 
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
