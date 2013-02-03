package ui.gui.frame;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ui.gui.event.IGuiEventListener;
import ui.gui.event.ILoginEventListener;
import ui.gui.event.IReListEventListener;
import ui.gui.event.LoginEvent;
import ui.gui.event.ReListEvent;
import ui.gui.panel.LoginPanel;
import ui.gui.panel.ReListPanel;

public class ControlFrame implements ILoginEventListener, IReListEventListener {

	List<IGuiEventListener> listeners;
	LoginPanel loginPanel;
	ReListPanel reListPanel;
	JFrame frame;
	JPanel controlPanel; // TODO to be re-factored into a separate class
	public ControlFrame() {
		initialize();
		addComponents();
		addListeners();
	}
	private void initialize() {
		listeners = new ArrayList<IGuiEventListener>();
		controlPanel = new JPanel();
		loginPanel = new LoginPanel();
		reListPanel = new ReListPanel();
		frame = new JFrame();

		controlPanel.setPreferredSize(new Dimension(300, 210));
		loginPanel.setPreferredSize(new Dimension(300, 150));
		reListPanel.setPreferredSize(new Dimension(300, 50));
	}
	
	private void addComponents() {
		
		GroupLayout layout = new GroupLayout(controlPanel);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		layout.setVerticalGroup(layout.createSequentialGroup().
				addComponent(loginPanel).
				addComponent(reListPanel)
			);
		frame.add(controlPanel);
	}
	
	private void addListeners() {
		loginPanel.addLoginEventListener(this);
		reListPanel.addReListEventListener(this);
	}
	
	private void attemptSystemLookAndFeel() {
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void display() {
		attemptSystemLookAndFeel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void addGuiEventListener(IGuiEventListener guiEventListener) {
		listeners.add(guiEventListener);	
	}
	
	private void dispatchLoginEvent(final IGuiEventListener listener, final LoginEvent loginEvent) {
		Runnable handler = new Runnable() {
			public void run() {
				listener.handleLoginEvent(loginEvent);
	        }
	    };  
	    new Thread(handler).start();	
	}
	
	private void dispatchReListEvent(final IGuiEventListener listener, final ReListEvent reListEvent) {
		Runnable handler = new Runnable() {
			public void run() {
				listener.handleReListEvent(reListEvent);
	        }
	    };  
	    new Thread(handler).start();	
	}
	
	private void notifyLoginListeners(LoginEvent loginEvent) {		
		for(final IGuiEventListener listener : listeners) {
			dispatchLoginEvent(listener, loginEvent);        
		}
	}
	
	private void notifyReListListeners(ReListEvent reListEvent) {
		for(final IGuiEventListener listener : listeners) {
			dispatchReListEvent(listener, reListEvent);
		}
	}
	
	@Override
	public void handle(ReListEvent reListEvent) {
		notifyReListListeners(reListEvent);
	}

	@Override
	public void handle(LoginEvent loginEvent) {
		notifyLoginListeners(loginEvent);
	}
	
}
