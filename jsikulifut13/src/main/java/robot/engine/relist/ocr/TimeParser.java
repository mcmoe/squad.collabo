package robot.engine.relist.ocr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeParser {

	private String s;
	private boolean parsed;
	private Time time;
	
	public TimeParser(String s) {
		this.s = s;
		parsed = false;
		time = new Time();
	}
	
	private void treat() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		s = s.replace('o', '0'); // look for 'o' replace with '0'
		s = s.replace('$', '8'); // look for '$' replace with '8'
		s = s.replace('z', '2'); // look for 'z' replace with '2'
		
		s = s.replace(':', 'i'); // look for ':' replace with 'i'
		s = s.replace("s8cs", "secs"); // look for s8cs replace with secs
		s = s.replace("s8c0nds", "seconds"); // look for s8c0nds replace with seconds
		s = s.toLowerCase(); // make lower case		
		logger.info("Treated String: {}", s);
	}
	private void parse() {
		if(!parsed) {
			Logger logger = LoggerFactory.getLogger(this.getClass());

			treat();

			// look for mins
			String[] minsAtZero = s.split("mins");
			if(minsAtZero.length > 1) {
				String mins = minsAtZero[0].trim();
				logger.info("Parsed mins: {}", mins);
				try {
					time.setMinutes(Integer.valueOf(mins)); // number here
					
					// all good, look for secs
					if(minsAtZero.length == 2) { // in case of ocr screw up
						String[] secsAtZero = minsAtZero[1].split("secs");
						if(secsAtZero.length > 1) {
							String secs = secsAtZero[0].trim();
							logger.info("Parsed secs: {}", secs);
							try {
								time.setSeconds(Integer.valueOf(secs)); // number here
							} catch(NumberFormatException e) {
								logger.info("secs not treated: {} ", e.getMessage());
							}
						}
					}
				} catch(NumberFormatException e) {
					time.setMinutes(Integer.valueOf(30)); // choose mid point as default
					logger.info("setting to 30 - mins not treated: {} ", e.getMessage());
				}
			} 
			else {
				// look for seconds
				String[] secondsAtZero = s.split("seconds");
				if(secondsAtZero.length > 1) {
					String seconds = secondsAtZero[0].trim();
					logger.info("Parsed seconds: {}", seconds);
					try {
						time.setSeconds(Integer.valueOf(seconds)); // number here
					} catch(NumberFormatException e) {
						time.setSeconds(Integer.valueOf(30)); // 30 secs default :p
						logger.info("settings to 30 - seconds not treated: {} ", e.getMessage());
					}
				}
			}
		}
	}
	public int getInMillis() {
		parse();
		return (time.getMinutes() * 60000) + (time.getSeconds() * 1000);
	}
	
	private class Time {
		private int seconds;
		private int minutes;
		
		public Time() {
			
		}

		public int getSeconds() {
			return seconds;
		}

		public void setSeconds(int seconds) {
			this.seconds = seconds;
		}

		public int getMinutes() {
			return minutes;
		}

		public void setMinutes(int minutes) {
			this.minutes = minutes;
		}
		
	}

}
