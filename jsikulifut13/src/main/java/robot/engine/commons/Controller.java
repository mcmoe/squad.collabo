package robot.engine.commons;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

public class Controller {
	
	private Mouse mouse;
	private Keyboard keyboard;
	private ScreenRegion screen;
	
	public Controller() {
		mouse = new DesktopMouse();
		keyboard = new DesktopKeyboard();		
		screen = new DesktopScreenRegion();
	}
	
	public void clickCenterOf(ScreenRegion r) {
		mouse.click(r.getCenter());
	}
	
	public void type(String text) {
		keyboard.type(text);
	}

	public ScreenRegion getScreen() {
		return screen;
	}
}
