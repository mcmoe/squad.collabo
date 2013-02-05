package robot;


import org.sikuli.api.ScreenRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import robot.region.SignInRegion;

public class Login {
	
	private String email;
	private char[] password;
	private SignInRegion signInRegion;
	Controller controller;
	
	public Login(Controller controller, String email, char[] password) {
		this.email = email;
		this.password = password;
		signInRegion = new SignInRegion(controller.getScreen());
		this.controller = controller;
	}
	
	private void typeInCredentials(ScreenRegion emailTexField) {
		controller.clickCenterOf(emailTexField);
        controller.type(email);
        controller.type("\t"); // tab to go to password field!   
        controller.type(new String(password));
	}
	
	private boolean fillAndSubmitCredentials(ScreenRegion emailTexField) {        
    	typeInCredentials(emailTexField);
    	
    	ScreenRegion signInButton = signInRegion.findSignInButton(500);
        
        if(signInButton == null) {
        	Logger logger = LoggerFactory.getLogger(this.getClass());
			logger.info("sign in button not found!");
        	return false;
        }
        
        controller.clickCenterOf(signInButton);
        
        return true;
	}
	
	private void signInIfRequired() {
		ScreenRegion emailTexField = signInRegion.findEmailTextField(15000);
        Logger logger = LoggerFactory.getLogger(this.getClass());
        
        if(emailTexField != null) {
        	logger.info("found email field signing in...");
        	fillAndSubmitCredentials(emailTexField);
        }
        
		logger.info("login not necessary - already signed in");
	}
	
	public void signIn() {
		signInIfRequired();	
	}
}
