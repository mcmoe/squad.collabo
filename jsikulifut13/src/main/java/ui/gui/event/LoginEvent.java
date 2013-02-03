package ui.gui.event;

public class LoginEvent {
	private final String email;
	private final char[] password;
	
	public LoginEvent(String email, char[] password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public char[] getPassword() {
		return password;
	}
}
