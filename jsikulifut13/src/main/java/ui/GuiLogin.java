package ui;

import ui.gui.frame.LoginScreen;

import robot.Engine;

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
		engine = new Engine();
		engine.launch();
		engine.signIn(loginScreen.getEmailText(), loginScreen.getPasswordText());
		engine.reList(); // for testing purposes, is here ... adapter to be re-factored with notification dev
	}
	
	public static void main(String[] args) {
		GuiLogin guiLogin = new GuiLogin();
		
		guiLogin.display();
	}
}
