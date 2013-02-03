package ui.robot;

import robot.Engine;
import ui.gui.event.IGuiEventListener;
import ui.gui.event.LoginEvent;
import ui.gui.event.ReListEvent;
import ui.gui.frame.ControlFrame;

public class GuiEngine implements IGuiEventListener{
	Engine engine;
	ControlFrame controlFrame;
	
	public GuiEngine() {
		engine = new Engine();
		controlFrame = new ControlFrame();
	}
	
	private void launchEngine() {
		engine.launch();
	}
	
	private void displayFrame() {
		controlFrame.display();
	}

	private void addFrameListener() {
		controlFrame.addGuiEventListener(this);
	}

	public void run() {
		addFrameListener();
		displayFrame();
	}
	
	@Override
	public void handleLoginEvent(LoginEvent loginEvent) {
		launchEngine();
		engine.signIn(loginEvent.getEmail(), loginEvent.getPassword());
	}
	
	@Override
	public void handleReListEvent(ReListEvent reListEvent) {
		engine.reList(reListEvent.getCycles()); 	
	}
	
	public static void main(String[] args) {
		GuiEngine guiEngine = new GuiEngine();
		guiEngine.run();
	}
}
