package no.steria.kata.exstartup;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnswerMachine {

	private Pattern patternAdd = Pattern.compile("what is (\\d+) plus (\\d+)");
	private Pattern patternMax = Pattern.compile("which of the following numbers is the largest: (.*)");
	private Pattern patternMultiply = Pattern.compile("what is (\\d+) multiplied by (\\d+)");
	private Pattern patternCost = Pattern.compile("how many dollars does one (Baloon|Banana|Apple) cost");
	private Pattern patternBuy = Pattern.compile("please put (\\d+) (Baloon|Banana|Apple) in my shopping cart");
	
	
	private final Map<String,Integer> productPrices = initShop();
	private int orderTotal = 0;

	public String answer(String question) {
		String check = checkAdd(question);
		if (check != null) return check;
		check = checkLargest(question);
		if (check != null) return check;
		check = checkMultiply(question);
		if (check != null) return check;
		check = checkWebshop(question);
		if (check != null) return check;
		return "Anders";
	}

	private String checkWebshop(String question) {
		if (question.endsWith("what products do you have for sale (comma separated)")) {
			return productList();
		}
		Matcher matcher = patternCost.matcher(question);
		if (matcher.find()) {
			return givePrice(matcher);
		}
		
		matcher = patternBuy.matcher(question);
		if (matcher.find()) {
			return shop(matcher);
		}
		if (question.endsWith("what is my order total")) {
			return "" + orderTotal;
		}
		return null;
	}

	private String shop(Matcher matcher) {
		int quantity =  Integer.parseInt(matcher.group(1));
		String productName = matcher.group(2);
		
		Integer price = productPrices.get(productName);
		if (price == null) {
			return "Dont know what " + productName + " costs";
		}
		
		int cost = price * quantity;
		orderTotal+=cost;
		
		return "Done";
	}

	private String givePrice(Matcher matcher) {
		String prodcutName = matcher.group(1);
		Integer price = productPrices.get(prodcutName);
		if (price == null) {
			return "Dont know what " + prodcutName + " costs";
		}
		return price.toString();
	}

	private String productList() {
		boolean first = true;
		StringBuilder res = new StringBuilder();
		for (String product : productPrices.keySet()) {
			if (!first) {
				res.append(",");
			}
			first=false;
			res.append(product);
		}
		return res.toString();
	}

	private Map<String, Integer> initShop() {
		Hashtable<String, Integer> result = new Hashtable<String, Integer>();
		result.put("Baloon", 5);
		result.put("Banana", 7);
		result.put("Apple", 11);
		return result;
	}

	private String checkLargest(String question) {
		Matcher matcher = patternMax.matcher(question);
		if (!matcher.find()) {
			return null;
		}
		String allNumbersString = matcher.group(1); 
		String[] allNumbers = allNumbersString.split(",");
		int max = -1;
		for (String numberstr : allNumbers) {
			max = Math.max(max, Integer.parseInt(numberstr.trim()));
		}
		return "" + max;
	}

	private String checkAdd(String question) {
		Matcher matcher = patternAdd.matcher(question);
		if (!matcher.find()) {
			return null;
		}
		int addOne = Integer.parseInt(matcher.group(1));
		int addTwo = Integer.parseInt(matcher.group(2));
		int result = addOne + addTwo;
		return "" + result;
	}
	
	private String checkMultiply(String question) {
		Matcher matcher = patternMultiply.matcher(question);
		if (!matcher.find()) {
			return null;
		}
		int addOne = Integer.parseInt(matcher.group(1));
		int addTwo = Integer.parseInt(matcher.group(2));
		int result = addOne * addTwo;
		return "" + result;
	}


}
