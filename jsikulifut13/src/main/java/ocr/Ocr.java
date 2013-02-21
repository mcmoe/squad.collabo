package ocr;

import java.awt.image.RenderedImage;

import com.asprise.util.ocr.OCR;

public class Ocr extends OCR{
	private final static String OCR_DLL = "/dll/AspriseOCR.dll";
	static {
		OCR.setLibraryPath(Ocr.class.getResource(OCR_DLL).getPath());
	}
	
	PopupEscaper popupEscaper;

	public Ocr() {
		super();
		popupEscaper = new PopupEscaper();
	}
	
	/* launch sikuli in separate thread wait for and escape (using 'n') the purchase screen */
	private void waitInParallelAndEscapePurchaseScreen() { 
		Thread thread = new Thread() {
			public void run() { 
				popupEscaper.escape();
			}
		};
		
		thread.start();
	}
	
	@Override
	public String recognizeCharacters(RenderedImage image) {
		waitInParallelAndEscapePurchaseScreen();
		return super.recognizeCharacters(image);
	}
}
