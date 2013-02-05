package robot;

import static org.sikuli.api.API.browse;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			Logger logger = LoggerFactory.getLogger(Browser.class);
			logger.info("MalformedURLException thrown by browser goTo");
			error = true;
		}
		return error;
	}

}
