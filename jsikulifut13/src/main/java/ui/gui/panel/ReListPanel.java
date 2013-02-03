package ui.gui.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.gui.event.IReListEventListener;
import ui.gui.event.ReListEvent;

public class ReListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3934205736026986082L;
	private List<IReListEventListener> listeners;
	private JPanel panel;
	private JLabel cyclesLabel;
	private JTextField cyclesText;
	private JButton reListButton;
	
	public ReListPanel() {
		initialize();
		addComponents();
		addListeners();
	}
	
	private void initialize() {
		listeners = new ArrayList<IReListEventListener>();
		panel = new JPanel(new GridLayout(1, 3));
		cyclesLabel = new JLabel("Cycles: ");
		cyclesText = new JTextField("1");
		reListButton = new JButton("ReList");	
		
		this.setLayout(new GridLayout(1, 1));
	}
	
	private void addComponents() {
		panel.add(cyclesLabel);
		panel.add(cyclesText);
		panel.add(reListButton);
		this.add(panel);
	}
	
	@Override
	public void setPreferredSize(Dimension dimension) {
		panel.setPreferredSize(dimension);
	}
	
	private void addListeners() {
		reListButton.addActionListener(new ActionPerformedListener());
	}
	
	private int getCycles() {
		return Integer.valueOf(getCyclesText());
	}
	
	private String getCyclesText() {
		return cyclesText.getText();
	}
	
	private void dispatchReListEvent(final IReListEventListener listener, final ReListEvent reListEvent) {
		Runnable handler = new Runnable() {
			public void run() {
				listener.handle(reListEvent);
	        }
	    };  
	    new Thread(handler).start();	
	}
	
	
	private void notifyListeners() {
		final ReListEvent reListEvent = new ReListEvent(getCycles());		
		for(final IReListEventListener listener : listeners) {
			dispatchReListEvent(listener, reListEvent);        
		}
	}
	
	public void addReListEventListener(IReListEventListener reListEventListener) {
		listeners.add(reListEventListener);
	}
	
	private class ActionPerformedListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			notifyListeners();			
		}
	}
	
	
	
	
}
