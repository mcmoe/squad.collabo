package adapter;

import java.net.MalformedURLException;

import engine.Login;
import gui.LoginScreen;

public class GuiLogin implements Runnable{
	
	LoginScreen loginScreen;
	Login login;
	
	public GuiLogin() {
		login = new Login();
		
	}
	
	public void display() {
		loginScreen = new LoginScreen(this);
		loginScreen.setVisible(true);
	}

	public void run() {
		try {
			login.launch(loginScreen.getEmailText(), loginScreen.getPasswordText());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GuiLogin guiLogin = new GuiLogin();
		
		guiLogin.display();
	}
}
