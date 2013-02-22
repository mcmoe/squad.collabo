package robot.engine.commons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ImageLogger {
	private static final String RESOURCE_LOG_PATH = ImageLogger.class.getResource("/log/").getPath();
	private static final ImageLogger SINGLETION = new ImageLogger();
	private static final String IMAGE_FORMAT = "png";
	
	DateFormat df;
	private ImageLogger() {
		df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		
	}
	
	private String getTimeStamp() {
		Calendar calender = Calendar.getInstance();
		calender.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		return df.format(calender.getTime());
	}
	
	private String getQualifiedName() {
		StringBuilder sb = new StringBuilder();
		sb.append(RESOURCE_LOG_PATH)
		.append(File.separator)
		.append(getTimeStamp())
		.append(".")
		.append(IMAGE_FORMAT);
		return sb.toString();
	}
	
	public void logToResource(BufferedImage snapshot) {
		Logger logger = LoggerFactory.getLogger(getClass());
		String qualifiedName = getQualifiedName();
		File outputfile = new File(qualifiedName);
	    try {
	    	logger.info("Writing Image File to: {}", outputfile);
			ImageIO.write(snapshot, IMAGE_FORMAT, outputfile);
			logger.info("Writing Image File - done");
		} catch (IOException e) {
			logger.info("Image Log aborted: {}", e.getMessage());
		}
	}
	
	public static ImageLogger getLogger() {
		return SINGLETION;
	}
}
