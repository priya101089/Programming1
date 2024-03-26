
public class Validator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean result_singleAtSign = singleAtSign("gmail.com");
		System.out.println(result_singleAtSign);
		
		String stringFetchBeforeAt = fetchBeforeAt("ganeshsanthar@gmail.com");
		System.out.println(stringFetchBeforeAt);
		
		String stringFetchAfterAt = fetchAfterAt("ganeshsanthar@gmail.com");
		System.out.println(stringFetchAfterAt);
	}

	//creating a dummy method
	public static boolean isAlphaNum(char charToValidate) {
		if((charToValidate >= 'a' && charToValidate <= 'z') || (charToValidate >= 'A' && charToValidate <= 'Z') || (charToValidate >= '0' && charToValidate <= '9')) {
			return true;
		}
		return false;
	}
	
	public static boolean isSpecialChar(char inputChar, boolean argument) {
		char[] acceptableValue = {'-','.'};
		boolean isSpecialChar = false;
		
		for (char n: acceptableValue) {
			if (n == inputChar) isSpecialChar = true;
		}
		
		if (argument) {
			if (inputChar == '_') isSpecialChar = true;
		}
		
		return isSpecialChar;
	}
	
	public static boolean isPrefixChar(char inputChar) {
		boolean isPrefixChar = (isSpecialChar(inputChar,true)||isAlphaNum(inputChar));
		return isPrefixChar;
	}
	
	public static boolean isDomainChar(char inputChar) {
		boolean isDomainChar = (isSpecialChar(inputChar,false)||isAlphaNum(inputChar));
		return isDomainChar;
	}
	

	
	public static boolean singleAtSign(String stringToValidate) {
		int count = 0;
		for(int i=0; i<stringToValidate.length();i++) {
			if(stringToValidate.charAt(i) == '@') {
				count++;
			}
		}
		if(count == 1) {
			return true;
		}
		return false;
		
	}
	
	public static String fetchBeforeAt(String stringToValidate) {
		String stringBeforeAt = "";
		for(int i=0; i<stringToValidate.length();i++) {
			if(stringToValidate.charAt(i) == '@') {
				break;
			}else {
				stringBeforeAt += stringToValidate.charAt(i);
			}
		}
		return stringBeforeAt;
	}
	
	public static String fetchAfterAt(String stringToValidate) {
		String stringAfterAt = "";
		boolean atFound = false;
		boolean startFetching = false;
		for(int i=0; i<stringToValidate.length();i++) {
			if(stringToValidate.charAt(i) == '@') {
				atFound = true;
			}
			if (atFound) {
				startFetching = true;
				atFound = false;
				continue;			
			}
			if(startFetching) {
				stringAfterAt += stringToValidate.charAt(i);
			}
		}
		return stringAfterAt;
	}
	
	public static boolean isPrefix(String stringToValidate) {
		
		if(stringToValidate.length() == 0) {
			return false;
		}
		if(!isAlphaNum(stringToValidate.charAt(0))){
			return false;
		}
		for(int i=0; i<stringToValidate.length();i++) {
			if(!isPrefixChar(stringToValidate.charAt(i))){
				return false;
			}
			if(isSpecialChar(stringToValidate.charAt(i), true)) {
				if(!isAlphaNum(stringToValidate.charAt(i+1))) {
					return false;
				}
			}
		}

		return true;
		
	}
	
	public static boolean isDomain(String stringToValidate) {
		String revFirstPortion="";
		String revSecondPortion="";
		String firstPortion="";
		String secondPortion="";
		for(int i=(stringToValidate.length()-1); i>=0;i--) {
			if((stringToValidate.charAt(i) == '.') && (stringToValidate.charAt(i-1) == '.')){
				return false;
			}else if((stringToValidate.charAt(i) == '.') && (isAlphaNum(stringToValidate.charAt(i-1)))){
				revFirstPortion += stringToValidate.charAt(i);
			}else {
				revSecondPortion += stringToValidate.charAt(i);
			}
		}
		for(int j=(revFirstPortion.length()-1); j>=0; j++) {
			firstPortion += revFirstPortion.charAt(j);
		}
		for(int k=(revSecondPortion.length()-1); k>=0; k++) {
			secondPortion += revSecondPortion.charAt(k);
		}
		if(!isAlphaNum(firstPortion.charAt(0))) {
			return false;
		}
		if(secondPortion.length() < 2) {
			return false;
		}
		for(int x=0; x<firstPortion.length(); x++) {
			if(!isDomainChar(firstPortion.charAt(x))){
				return false;
			}
			if((firstPortion.charAt(x) == '.' && !isAlphaNum(firstPortion.charAt(x+1))) || (firstPortion.charAt(x) == '-' && !isAlphaNum(firstPortion.charAt(x+1)))){
				return false;
			}
		}
		for(int x=0; x<secondPortion.length(); x++) {
			if(!(secondPortion.charAt(x) >= 'a' && secondPortion.charAt(x) <= 'z') && !(secondPortion.charAt(x) >= 'A' && secondPortion.charAt(x) <= 'Z')) {
				return false;
			}
		}
			
		return true;
	}
	public static boolean isEmail(String stringToValidate) {
		String emailPrefix = "";
		String emailDomain = "";
		if(!singleAtSign(stringToValidate)) {
			return false;
		}
		emailPrefix = fetchBeforeAt(stringToValidate);
		emailDomain = fetchAfterAt(stringToValidate);
		if(!isPrefix(emailPrefix)) {
			return false;
		}
		if(!isDomain(emailDomain)) {
			return false;
		}
		
		return true;
	}
	public static String[] validEmails(String[] emailsToValidate) {
		String validEmails[] = {};
		int i=0;
		for (String emailString: emailsToValidate) {
			if(isEmail(emailString)) {
				validEmails[i] = emailString;
				i++;
			}
		}		
		return validEmails;
	}
	
	
	
}
