package gui.fut;

import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;

public class TradePileRegion {

	private Class<?> targetClass;
	ScreenRegion screen;
	Target tradingTab;
	Target tradePileTab;
	Target expiredItem;
	Target buttonList;
	Target highlightedButtonList;
	Target coinsPointsRegion;
	Target activeNextPage;
	Target highlightedNextPage;
	Target activePreviousPage;
	Target highlightedPreviousPage;
	
	public TradePileRegion(ScreenRegion screen) {
		this.screen = screen;
		targetClass = getClass();
		
		tradingTab = new ImageTarget(targetClass.getResource("/Trading.png"));
		tradePileTab = new ImageTarget(targetClass.getResource("/TradePile.png")); 
		expiredItem = new ImageTarget(targetClass.getResource("/Xpired.png"));
		buttonList = new ImageTarget(targetClass.getResource("/ButtonList.png"));
		highlightedButtonList = new ImageTarget(targetClass.getResource("/ButtonHighlightedList.png"));
		coinsPointsRegion = new ImageTarget(targetClass.getResource("/CoinsFIFAPoints.png"));
		activeNextPage = new ImageTarget(targetClass.getResource("/ArrowActiveRight.png"));
		highlightedNextPage = new ImageTarget(targetClass.getResource("/ArrowActiveHighlightedRight.png"));
		activePreviousPage = new ImageTarget(targetClass.getResource("/ArrowActiveLeft.png"));
		highlightedPreviousPage = new ImageTarget(targetClass.getResource("/ArrowActiveHighlightedLeft.png"));
	}
	
	public ScreenRegion findTradingTab(int millis) {
        return screen.wait(tradingTab, millis);
	}

	public ScreenRegion findTradePileTab(int millis) {
        return screen.wait(tradePileTab, millis);
	}
	
	public ScreenRegion findXpiredItem(int millis) {
        return screen.wait(expiredItem, millis);
	}

	public ScreenRegion findReListButton(int millis) {
		ScreenRegion button = screen.wait(buttonList, millis/2);	
		if(button == null) {
			button = screen.wait(highlightedButtonList, millis/2);	
		}
		return button;
	}

	public ScreenRegion findCoinsPointsRegion(int millis) {
		return screen.wait(coinsPointsRegion, millis); 
	}
	
	public ScreenRegion findNextPage(int millis) {
		ScreenRegion nextPage = screen.wait(activeNextPage, millis/2);	
		if(nextPage == null) {
			nextPage = screen.wait(highlightedNextPage, millis/2);	
		}
		return nextPage;
	}
	
	public ScreenRegion findPreviousPage(int millis) {
		ScreenRegion previousPage = screen.wait(activePreviousPage, millis/2);	
		if(previousPage == null) {
			previousPage = screen.wait(highlightedPreviousPage, millis/2);	
		}
		return previousPage;
	}
}
