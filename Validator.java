
public class Validator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	
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
	

	//Method to check if a String contain a single at sign (@)
	//input : String to validate
	//output : boolean true - only one @, false - 0 or >1
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
	
	//Method to get the beginning of an email address
	//input : String to validate
	//output : String containing the portion before the @ symbol.
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
	
	//Method to get the ending of an email address.
	//input : String to validate
	//output : String containing the portion after the @ symbol.
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
	//Method to check if the start of a String is a valid email prefix.
	//input : String to validate
	//output : true if the following conditions are met, false otherwise. 
	/*Contains at least one character.
	» Contains only alphanumeric characters, underscores (_), periods (.), and dashes (-).
	» An underscore, period, or dash must always be followed by at least one alphanumeric characters.
	» First character is alphanumeric*/
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
	
	//Method to check if the end of a String is a valid email domain.
	//input : String to validate
	//output : true if the following conditions are met, false otherwise.
	/*Made up of two portions separated by a period.
	» First portion must start with an alphanumeric character.
	» Second portion contains at least two characters.
	» First portion contains only alphanumeric characters, periods, and dashes.
	» A period or dash, in the first portion must be followed by one or more alphanumeric characters.
	» The second portion contains only letters of the alphabet*/
	public static boolean isDomain(String stringToValidate) {
		String revFirstPortion="";
		String revSecondPortion="";
		String firstPortion="";
		String secondPortion="";
		boolean firstPortionFlag = false;
		for(int i=(stringToValidate.length()-1); i>=0;i--) {
			if((stringToValidate.charAt(i) == '.') && (stringToValidate.charAt(i-1) == '.')){
				return false;
			}else if((stringToValidate.charAt(i) == '.') && (isAlphaNum(stringToValidate.charAt(i-1)))){
				firstPortionFlag = true;				
				continue;
			}else {
				if(!firstPortionFlag) {
					revSecondPortion += stringToValidate.charAt(i);
				}
			}
			if(firstPortionFlag) {				
				revFirstPortion += stringToValidate.charAt(i);
			}
			
		}
		for(int j=(revFirstPortion.length()-1); j>=0; j--) {
			firstPortion += revFirstPortion.charAt(j);
		}
		for(int k=(revSecondPortion.length()-1); k>=0; k--) {
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
	
	//Method to check if a string is a valid email address.
	//input : String to validate
	//output : true if the String is a valid email address which consists of a prefix, '@' symbol, and domain., false otherwise.
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
	
	//Method to check if Strings contained in an array of String are valid email addresses.
	//input : emails to validate
	//output : array of Strings containing only the emails that are considered valid.
	public static String[] validEmails(String[] emailsToValidate) {
		String tempValidEmails[] = new String[emailsToValidate.length];
		int i=0;
		for (String emailString: emailsToValidate) {
			if(isEmail(emailString)) {
				tempValidEmails[i] = emailString;
				i++;
			}
		}	
		String validEmails[] = new String[i];
		for(int j=0; j<tempValidEmails.length; j++) {
			if(tempValidEmails[j] == null) {
				continue;
			}
			validEmails[j] = tempValidEmails[j];
		}
		return validEmails;
	}
	
	
	
}
