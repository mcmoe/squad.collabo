package robot;


import org.sikuli.api.ScreenRegion;

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
        	System.out.println("sign in button not found!"); // TODO: logger
        	return false;
        }
        
        controller.clickCenterOf(signInButton);
        
        return true;
	}
	
	private void signInIfRequired() {
		ScreenRegion emailTexField = signInRegion.findEmailTextField(15000);
		
        if(emailTexField != null) {
        	System.out.println("found email field signing in..."); // TODO: logger
        	fillAndSubmitCredentials(emailTexField);
        }
	}
	
	public void signIn() {
		signInIfRequired();	
	}
}
