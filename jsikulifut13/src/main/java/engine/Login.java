package engine;

import java.net.MalformedURLException;
import java.net.URL;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import static org.sikuli.api.API.*;

public class Login {
	
	private String email;
	private char[] password;
	private Mouse mouse;
	private Keyboard keyboard;
	
	public Login() {
        mouse = new DesktopMouse();
		keyboard = new DesktopKeyboard();
	}
	
	private void signIn(ScreenRegion s, Class<?> targetClass) {
        URL emailSnapshotURL = targetClass.getResource("/Email.png");                
        Target imageTarget = new ImageTarget(emailSnapshotURL);
        ScreenRegion r = s.wait(imageTarget,5000);
        
        mouse.click(r.getCenter());
        keyboard.type(email);
        keyboard.type("\t");        
        keyboard.type(new String(password));
        
        URL signInURL = targetClass.getResource("/SignIn.png");                
        imageTarget = new ImageTarget(signInURL);
        r = s.wait(imageTarget,5000);
        
        mouse.click(r.getCenter());
	}

	public void launch(String email, char[] password) throws MalformedURLException {
		
		this.email = email;
		this.password = password;
		
		browse(new URL("http://www.ea.com/uk/football/fifa-ultimate-team"));

        ScreenRegion s = new DesktopScreenRegion();
  
        Class<?> targetClass = getClass();
        
        URL coinsPointsSnapshotURL = targetClass.getResource("/CoinsFIFAPoints.png");               
        Target imageTarget = new ImageTarget(coinsPointsSnapshotURL);

        ScreenRegion r = s.wait(imageTarget,50000);
        
        if(r == null) {
        	System.out.println("signing in...");
        	signIn(s, targetClass);
        }
        
        // navigate to trade pile
        {
	       	 URL tradingSnapshotURL = targetClass.getResource("/Trading.png");               
	         /*Target*/ imageTarget = new ImageTarget(tradingSnapshotURL);
	    	
	         System.out.println("looking for Trading...");
	         /* ScreenRegion */ r = s.wait(imageTarget,5000);
	        
	         mouse.click(r.getCenter());
	         
	         URL tradePileSnapshotURL = targetClass.getResource("/TradePile.png");               
	         /*Target*/ imageTarget = new ImageTarget(tradePileSnapshotURL);
	    	
	         System.out.println("looking for Trade Pile...");
	         /* ScreenRegion */ r = s.wait(imageTarget,5000);
	         
	         mouse.click(r.getCenter());
	         
	         URL xpiredSnapshotURL = targetClass.getResource("/Xpired.png");               
	         /*Target*/ imageTarget = new ImageTarget(xpiredSnapshotURL);
	    	
	         System.out.println("waiting for xpired...");
	         /* ScreenRegion */ r = s.wait(imageTarget,5000);
	         
	         if(r!= null) {
		         mouse.click(r.getCenter());
		         
		         URL listSnapshotURL = targetClass.getResource("/ButtonList.png");               
		         /*Target*/ imageTarget = new ImageTarget(listSnapshotURL);
		         
		         System.out.println("waiting for list button...");
		         /* ScreenRegion */ r = s.wait(imageTarget,5000);
		         
		         mouse.click(r.getCenter());
	         }
        }
	}
}
