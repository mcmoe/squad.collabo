package robot.engine.relist;

import org.sikuli.api.ScreenRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import robot.engine.commons.Controller;
import robot.engine.relist.ocr.SmartSleep;
import robot.region.TradePileRegion;

public class ReList {
	private static final int MILLIS_TO_MINUTES = 60000;

	int cycles;
	int page;
	Controller controller;
	TradePileRegion tradePileRegion;
	SmartSleep smartSleep;
	
	public ReList(Controller controller, int cycles) {
		this.cycles = cycles;
		page = 1;
		this.controller = controller;
		tradePileRegion = new TradePileRegion(controller.getScreen());
		smartSleep = new SmartSleep(controller, tradePileRegion);
	}
	
	private void navigateToTrading() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("looking for Trading...");
		ScreenRegion trading = tradePileRegion.findTradingTab(5000); 

		if(trading != null) {
			logger.info("clicking on trading...");
			controller.clickCenterOf(trading);
		}
	}
	
	private void navigateToTradePile() {		
		navigateToTrading();
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("looking for Trade Pile...");
        ScreenRegion tradePileTab = tradePileRegion.findTradePileTab(5000);
        
        if(tradePileTab != null) {
        	logger.info("clicking on tradePile...");
        	controller.clickCenterOf(tradePileTab);
        }
	}
	
	private void reListActiveItems() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("waiting for list button...");
        ScreenRegion relistButton = tradePileRegion.findReListButton(6000);
        
        while(relistButton != null) {
        	controller.clickCenterOf(relistButton);
        	relistButton = tradePileRegion.findReListButton(3000);
        }
	}
	
	private void reListCurrentPage() {
		ScreenRegion expiredItem = null;
		reListActiveItems();
		do {
			Logger logger = LoggerFactory.getLogger(this.getClass());
			logger.info("waiting for xpired...");
	        expiredItem = tradePileRegion.findXpiredItem(3000);
	         
	        if(expiredItem != null) {
		        controller.clickCenterOf(expiredItem);
		        reListActiveItems();
		    }
		}while(expiredItem != null);
	}
	
	private boolean nextPage() {
		boolean hasNextPage = false;
		ScreenRegion nextPage = tradePileRegion.findNextPage(1000);
		
		if(nextPage != null) {
			Logger logger = LoggerFactory.getLogger(this.getClass());
			logger.info("clicking next page...");
			hasNextPage = true;
			controller.clickCenterOf(nextPage);
			page++;
		}
		
		return hasNextPage;
	}
	
	private void reListAllPages() {
		do {
			reListCurrentPage();
		} while (nextPage());
	}
	
	private void navigateToFirstPage() {
		while(page > 1) {
			ScreenRegion previousPage = tradePileRegion.findPreviousPage(1000);
			
			if(previousPage != null) {
				Logger logger = LoggerFactory.getLogger(this.getClass());
				logger.info("clicking previous page...");
				controller.clickCenterOf(previousPage);
				page--;
			}
		}
	}
	
	private void intelligentSleep() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			int requiredTime = smartSleep.getRequiredSleepTime();
			logger.info("sleeping for {} mins *zZZzZzZz*", (double) requiredTime/MILLIS_TO_MINUTES);
			Thread.sleep(requiredTime);
		} catch (InterruptedException e) {
			logger.info("Thread Interruppted - quiting...");
			e.printStackTrace();
		}		
	}
	
	private boolean quitOrSleep(int times) {
		if(times >= cycles) {
			return true;
		}		
		intelligentSleep();
		return false;
	}
	
	public void reList() {	
		int times = 0;
		boolean quit = false;
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("looking for Coins/Points to ensure proper login...");
		if(tradePileRegion.findCoinsPointsRegion(20000) != null) {
			do {
				navigateToTradePile();	        
				reListAllPages();
				//nextPage(); // DEBUG
				navigateToFirstPage();
				times++;
				logger.info("{} / {} reList Complete);", times, cycles);
				quit = quitOrSleep(times);
			}while(!quit);
		}        
		logger.info("exiting reList...");
	}
}
