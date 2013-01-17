package engine;

import gui.fut.TradePileRegion;
import org.sikuli.api.ScreenRegion;

public class ReList {
	
	int page;
	Controller controller;
	TradePileRegion tradePileRegion;
	
	public ReList(Controller controller) {
		page = 1;
		this.controller = controller;
		tradePileRegion = new TradePileRegion(controller.getScreen());
	}
	
	private void navigateToTradePile() {
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
	}
	
	private void reListCurrentPage() {
        System.out.println("waiting for xpired..."); // TODO: logger      
        ScreenRegion expiredItem = tradePileRegion.findXpiredItem(3000);
         
        while(expiredItem != null) {
	        controller.clickCenterOf(expiredItem);
	        
	        System.out.println("waiting for list button..."); // TODO: logger
	        ScreenRegion relistButton = tradePileRegion.findReListButton(3000);
	        
	        while(relistButton != null) {
	        	controller.clickCenterOf(relistButton);
	        	relistButton = tradePileRegion.findReListButton(1000);
	        }
	        
	        System.out.println("waiting for more xpired..."); // TODO: logger
	        expiredItem = tradePileRegion.findXpiredItem(3000);
        }
	}
	
	private boolean nextPage() {
		boolean hasNextPage = false;
		ScreenRegion nextPage = tradePileRegion.findNextPage(1000);
		
		if(nextPage != null) {
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
				controller.clickCenterOf(previousPage);
				page--;
			}
		}
	}
	
	public void reList() {	
		// navigate to trade pile		
		System.out.println("looking for Coins/Points to ensure proper login..."); // TODO: logger
		if(tradePileRegion.findCoinsPointsRegion(20000) != null) {		
			navigateToTradePile();	        
			reListAllPages();
			navigateToFirstPage();
		}
        
        System.out.println("exiting reList..."); // TODO: logger
	}
}
