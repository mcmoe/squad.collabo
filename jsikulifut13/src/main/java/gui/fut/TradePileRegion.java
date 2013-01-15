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
	Target coinsPointsRegion;
	
	public TradePileRegion(ScreenRegion screen) {
		this.screen = screen;
		targetClass = getClass();
		
		tradingTab = new ImageTarget(targetClass.getResource("/Trading.png"));
		tradePileTab = new ImageTarget(targetClass.getResource("/TradePile.png")); 
		expiredItem = new ImageTarget(targetClass.getResource("/Xpired.png"));
		buttonList = new ImageTarget(targetClass.getResource("/ButtonList.png"));
		coinsPointsRegion = new ImageTarget(targetClass.getResource("/CoinsFIFAPoints.png"));
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
        return screen.wait(buttonList, millis); 
	}

	public ScreenRegion findCoinsPointsRegion(int millis) {
		return screen.wait(coinsPointsRegion, millis); 
	}
}
