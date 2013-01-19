package gui.fut;

import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;

public class SignInRegion {
	
	private Class<?> targetClass;
	private Target emailField;
	private Target signInButton;
	ScreenRegion s;
	
	public SignInRegion(ScreenRegion s) {
		this.s = s;
		targetClass = getClass();
		emailField = new ImageTarget(targetClass.getResource("/Email.png"));
		signInButton = new ImageTarget(targetClass.getResource("/SignIn.png"));
	}
	
	public ScreenRegion findEmailTextField(int millis) {
        return s.wait(emailField, millis);
	}
	
	public ScreenRegion findSignInButton(int millis) {
        return s.wait(signInButton, millis);
	}
}
