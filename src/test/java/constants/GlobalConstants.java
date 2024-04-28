package constants;

public class GlobalConstants {
	
	public static final String coreProductUrl = "https://www.nba.com/warriors";
	public static final String derivedProduct1Url = "https://www.nba.com/sixers/";
	public static final String derivedProduct2Url = "https://www.nba.com/bulls/";
	
	public static String getTestUrl(String product) {
		String url = "";
		if (product.equalsIgnoreCase("coreProduct")) {
			url = GlobalConstants.coreProductUrl;
		} else if (product.equalsIgnoreCase("derivedProduct1")) {
			url = GlobalConstants.derivedProduct1Url;
		} else if (product.equalsIgnoreCase("derivedProduct2")) {
			url = GlobalConstants.derivedProduct2Url;
		}
		return url;
	}

}
