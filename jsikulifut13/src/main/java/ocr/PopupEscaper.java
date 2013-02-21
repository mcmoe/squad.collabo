package ocr;

import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import robot.engine.commons.Controller;

public class PopupEscaper {
	private Target popup;
	private Controller controller;
	
	public PopupEscaper() {
		popup = new ImageTarget(getClass().getResource("/AspriseOcrDialogBoxEnabled.png"));
		controller = new Controller();
	}
	
	public void escape() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("PopUpEscaper: waiting for popup");
		ScreenRegion r = controller.getScreen().wait(popup, 5000);
		
		if(r != null) {
			logger.info("popupEscaper: found popup - escaping");
			controller.clickCenterOf(r);
			controller.type("n");
			logger.info("popupEscaper: escaped");
		} else {
			logger.info("popupEscaper: did not find popup!");
		}
	}
}
