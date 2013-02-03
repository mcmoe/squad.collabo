package ui.gui.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.gui.event.ILoginEventListener;
import ui.gui.event.LoginEvent;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = -4873504780181956511L;
	JPanel panel;
	JPanel panelCredentials;
	JButton buttonLogin;
	
	JLabel emailLabel;
	JTextField emailText;

	JLabel passwordLabel;
	JPasswordField passwordText;
	
	List<ILoginEventListener> listeners;
	
	public LoginPanel() {
		initialize();		
		addListeners();
		addComponents();
	}
	
	private void initialize() {
		panel = new JPanel(new GridLayout(2, 1));
		panelCredentials = new JPanel(new GridLayout(2, 2));
	
		emailLabel = new JLabel("Email: ");
		emailText = new JTextField("");
		passwordLabel = new JLabel("Password: ");
		passwordText = new JPasswordField("");	
		buttonLogin = new JButton("Login");
		
		listeners = new ArrayList<ILoginEventListener>();
		
		this.setLayout(new GridLayout(1, 1));
	}
	
	private void addListeners() {
		passwordText.addKeyListener(new EnterKeyListener());
		buttonLogin.addKeyListener(new EnterKeyListener());
		buttonLogin.addActionListener(new ActionPerformedListener());
	}
	
	private void addComponents() {
		panelCredentials.add(emailLabel);
		panelCredentials.add(emailText);	
		panelCredentials.add(passwordLabel);
		panelCredentials.add(passwordText);
		
		panel.add(panelCredentials);
		panel.add(buttonLogin);
		
		this.add(panel);
	}
	
	@Override
	public void setPreferredSize(Dimension dimension) {
		panel.setPreferredSize(dimension);
	}
	
	public String getEmailText() {
		return emailText.getText();
	}

	public char[] getPasswordText() {
		return passwordText.getPassword();
	}
	
	private void dispatchLoginEvent(final ILoginEventListener listener, final LoginEvent loginEvent) {
		Runnable handler = new Runnable() {
			public void run() {
				listener.handle(loginEvent);
	        }
	    };  
	    new Thread(handler).start();	
	}
	
	private void notifyListeners() {
		final LoginEvent loginEvent = new LoginEvent(getEmailText(), getPasswordText());		
		for(final ILoginEventListener listener : listeners) {
			dispatchLoginEvent(listener, loginEvent);        
		}
	}

	public void addLoginEventListener(ILoginEventListener loginEventListener) {
		listeners.add(loginEventListener);
	}
	
	private class EnterKeyListener implements KeyListener {
		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == KeyEvent.VK_ENTER) { 
				notifyListeners();
			} 
		}	
	}
	
	private class ActionPerformedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			notifyListeners();			
		}
	}
}
