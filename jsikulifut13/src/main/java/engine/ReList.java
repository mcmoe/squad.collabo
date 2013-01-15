package engine;

import gui.fut.TradePileRegion;
import org.sikuli.api.ScreenRegion;

public class ReList {
	
	Controller controller;
	TradePileRegion tradePileRegion;
	
	public ReList(Controller controller) {
		this.controller = controller;
		tradePileRegion = new TradePileRegion(controller.getScreen());
	}
	
	public void reList() {
		
		// navigate to trade pile		
		System.out.println("looking for Coins/Points to ensure proper login..."); // TODO: logger
		if(tradePileRegion.findCoinsPointsRegion(5000) != null) {
			
			// navigate to trade pile		
			System.out.println("looking for Trading..."); // TODO: logger
			ScreenRegion trading = tradePileRegion.findTradingTab(5000); 
			// this should be worked around as to not have to wait 7000 milliseconds to ensure refresh after login
	        
			if(trading != null) {
				System.out.println("clicking on trading..."); // TODO: logger
				controller.clickCenterOf(trading);
			}
	
			System.out.println("looking for Trade Pile..."); // TODO: logger	
	        ScreenRegion tradePileTab = tradePileRegion.findTradePileTab(5000);
	        
	        if(tradePileTab != null) {
	        	System.out.println("clicking on tradePile..."); // TODO: logger
	        	controller.clickCenterOf(tradePileTab);
	        }
	        
	        System.out.println("waiting for xpired..."); // TODO: logger      
	        ScreenRegion expiredItem = tradePileRegion.findXpiredItem(5000);
	         
	        if(expiredItem != null) {
		        controller.clickCenterOf(expiredItem);
		        
		        System.out.println("waiting for list button..."); // TODO: logger
		        ScreenRegion relistButton = tradePileRegion.findReListButton(5000);
		        
		        controller.clickCenterOf(relistButton);
	        }
		}
        
        System.out.println("exiting reList..."); // TODO: logger
	}

}
