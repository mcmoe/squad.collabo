package ui.gui.event;

public interface IGuiEventListener {
	//public void onNotify();

	public void handleLoginEvent(LoginEvent loginEvent);
	public void handleReListEvent(ReListEvent reListEvent);
}
