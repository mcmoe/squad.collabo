package adapter;

import engine.Engine;
import gui.LoginScreen;

import java.net.MalformedURLException;

public class GuiLogin implements Runnable{
	
	LoginScreen loginScreen;
	Engine engine;
	
	public GuiLogin() {
	}
	
	public void display() {
		loginScreen = new LoginScreen(this);
		loginScreen.setVisible(true);
	}

	public void run() {
		try {
			engine = new Engine();
			engine.launch();
			engine.signIn(loginScreen.getEmailText(), loginScreen.getPasswordText());
			engine.reList(); // for testing purposes, is here ... adapter to be re-factored with notification dev
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GuiLogin guiLogin = new GuiLogin();
		
		guiLogin.display();
	}
}
