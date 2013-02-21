package robot.engine;

import robot.engine.commons.Browser;
import robot.engine.commons.Controller;
import robot.engine.login.Login;
import robot.engine.relist.ReList;

public class Engine {
	Controller controller;
	
	public Engine() {
		controller = new Controller();
	}
	
	public void launch() {
		Browser.goTo("http://www.ea.com/uk/football/fifa-ultimate-team");
	}
	
	public void signIn(String email, char[] password) {
		Login login = new Login(controller, email, password);
		login.signIn();
	}
	
	public void reList(int cycles) {
		ReList reList = new ReList(controller, cycles);
		reList.reList();
	}

}
