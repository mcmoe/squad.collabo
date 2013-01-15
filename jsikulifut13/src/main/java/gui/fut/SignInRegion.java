package gui.fut;

import java.net.URL;

import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;

public class SignInRegion {
	
	private Class<?> targetClass;
	private Target imageTarget;
	private URL emailSnapshotURL;
	private URL signInSnapshotURL;
	ScreenRegion s;
	
	public SignInRegion(ScreenRegion s) {
		this.s = s;
		targetClass = getClass();
		emailSnapshotURL = targetClass.getResource("/Email.png");
		signInSnapshotURL = targetClass.getResource("/SignIn.png");
	}
	
	public ScreenRegion findEmailTextField(int millis) {                
        imageTarget = new ImageTarget(emailSnapshotURL);
        return s.wait(imageTarget, millis);
	}
	
	public ScreenRegion findSignInButton(int millis) {               
        imageTarget = new ImageTarget(signInSnapshotURL);
        return s.wait(imageTarget, millis);
	}
}
