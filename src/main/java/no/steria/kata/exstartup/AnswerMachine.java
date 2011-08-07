package no.steria.kata.exstartup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnswerMachine {

	private Pattern patternAdd = Pattern.compile("what is (\\d+) plus (\\d+)");
	private Pattern patternMax = Pattern.compile("which of the following numbers is the largest: (.*)");
	private Pattern patternMultiply = Pattern.compile("what is (\\d+) multiplied by (\\d+)");

	public String answer(String question) {
		String check = checkAdd(question);
		if (check != null) return check;
		check = checkLargest(question);
		if (check != null) return check;
		check = checkMultiply(question);
		if (check != null) return check;
		return "Anders";
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
