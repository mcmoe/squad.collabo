package robot;

import static org.sikuli.api.API.browse;

import java.net.MalformedURLException;
import java.net.URL;

public class Browser {
	
	/**
	 * @param String:url web address to browse to
	 * @return boolean:error true if MalformedURLException caught
	 */
	public static boolean goTo(String url) {
		boolean error = false;
		try {
			browse(new URL("http://www.ea.com/uk/football/fifa-ultimate-team"));
		} catch (MalformedURLException e) {
			error = true;
			System.out.println("MalformedURLException thrown by browser goTo"); // TODO Logger
		}
		return error;
	}

}
