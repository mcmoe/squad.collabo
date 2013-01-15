package engine;

import static org.sikuli.api.API.browse;

import java.net.MalformedURLException;
import java.net.URL;

public class Engine {
	Controller controller;
	
	public Engine() {
		controller = new Controller();		
	}
	
	public void launch() throws MalformedURLException {
		browse(new URL("http://www.ea.com/uk/football/fifa-ultimate-team"));
	}
	
	public void signIn(String email, char[] password) {
		Login login = new Login(controller, email, password);
		login.signIn();
	}
	
	public void reList() {
		ReList reList = new ReList(controller);
		reList.reList();
	}

}
