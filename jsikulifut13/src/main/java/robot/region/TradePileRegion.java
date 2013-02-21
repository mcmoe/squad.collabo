package robot.region;

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
	Target currentActionsBar;
	Target timeRemaining;
	
	public TradePileRegion(ScreenRegion screen) {
		this.screen = screen;
		targetClass = getClass();
		
		tradingTab = new ImageTarget(targetClass.getResource("/Trading.png"));
		tradePileTab = new ImageTarget(targetClass.getResource("/TradePile.png")); 
		expiredItem = new ImageTarget(targetClass.getResource("/Xpired.png"));
		buttonList = new ImageTarget(targetClass.getResource("/ButtonList.png"));
		highlightedButtonList = new ImageTarget(targetClass.getResource("/ButtonHighlightedList.png"));
		coinsPointsRegion = new ImageTarget(targetClass.getResource("/CoinsFIFAPoints.png"));
		activePreviousPage = new ImageTarget(targetClass.getResource("/ArrowActiveLeft.png"));
		highlightedPreviousPage = new ImageTarget(targetClass.getResource("/ArrowActiveHighlightedLeft.png"));	
		activeNextPage = new ImageTarget(targetClass.getResource("/ArrowActiveRight.png"));
		highlightedNextPage = new ImageTarget(targetClass.getResource("/ArrowActiveHighlightedRight.png"));
		currentActionsBar = new ImageTarget(targetClass.getResource("/CurrentActionsBar.png"));
		timeRemaining = new ImageTarget(targetClass.getResource("/TimeRemaining.png"));
		
		// high accuracy is needed to differentiate from disabled next page button
		activeNextPage.setMinScore(0.98);
		highlightedNextPage.setMinScore(0.98);
		// high accuracy also needed for actions bar (differntiate from sold items bar etc...)
		currentActionsBar.setMinScore(0.999);
		
		// more efficient as FUT automatically loads the right neighbor of a card that has just been re-listed.
		// this in turn allows the continuous re-listing of a whole page without reselecting another card.
		expiredItem.setOrdering(Target.Ordering.LEFT_RIGHT);
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
	
	private ScreenRegion findCurrentActionsBar(int millis) {
		return screen.wait(currentActionsBar, millis);
	}
	
	public ScreenRegion findFirstCard(int millis) {
		ScreenRegion firstCard = null;
		ScreenRegion bar = findCurrentActionsBar(millis);
		if(bar != null) {
			int width = (int) bar.getBounds().getWidth();
			int height = (int) bar.getBounds().getHeight();
			firstCard = bar.getRelativeScreenRegion(width, 0, width*2, height);	
		}		
		return firstCard;
	}
	
	public ScreenRegion findTimeRemaining(int millis) {
		ScreenRegion timeBox = null;
		ScreenRegion label = screen.wait(timeRemaining, millis);
		if(label != null) {
			int width = (int) label.getBounds().getWidth();
			int height = (int) label.getBounds().getHeight();
			timeBox = label.getRelativeScreenRegion(width, 0, width, height);
		}
		return timeBox;
	}
}
