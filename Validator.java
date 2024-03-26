
public class Validator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	//method to check if nothing gets repeated inside of a char array
	public static boolean noRepeat(char[] input) {
		
		for (int i=0; i< input.length;i++) {
			for (int a =0; a<input.length; a++) {
				if (input[i] == input[a] && a!=i) return false; 
			}
		}
		
		return true;
	}
	
	//method to chec if char array has at least one alphanum value 
	public static boolean hasOneAlphaNum(char[] input) {
		int counter = 0;
		for (int i = 0; i<input.length;i++) 
			if (isAlphaNum(input[i])) counter++;
		
		if(counter > 0) return true;
		return false;
	}

	//method to check if a char after dot or dash is alphanumeric
	public static boolean textAfterDot(char[] input) {
		for(int i=0;i<input.length;i++) {
			if(isSpecialChar(input[i],true)) {
				if(isAlphaNum(input[i+1])) return true;
			}
		}
	}
	
	public static boolean isAlphaNum(char charToValidate) {
		//setting condition using char expressions
		if((charToValidate >= 'a' && charToValidate <= 'z') || (charToValidate >= 'A' && charToValidate <= 'Z') || (charToValidate >= '0' && charToValidate <= '9')) {
			return true;
		}
		return false;
	}
	
	public static boolean isSpecialChar(char inputChar, boolean argument) {
		//setting a pool of acceptable values
		char[] acceptableValue = {'-','.'};
		boolean isSpecialChar = false;
		
		//iterating through loop to check for match between input and pool of accepted values
		for (char n: acceptableValue) {
			if (n == inputChar) isSpecialChar = true;
		}
		
		if (argument) {
			if (inputChar == '_') isSpecialChar = true;
		}
		
		return isSpecialChar;
	}
	
	//combining alphanum and special char for the next two methods
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
	

	public static String isUsername(String username) {
		char[] usernameArr = username.toCharArray();
		//initializing empty return from beginning
		String lowcaseUsername = "";
		
		
		boolean isSpecialChar = isSpecialChar(usernameArr[0],false);
		//System.out.println(isSpecialChar); test
		
		boolean hasOneAlphaNum = hasOneAlphaNum(usernameArr);
		//System.out.println(hasOneAlphaNum); test
		boolean textAfterDot = textAfterDot(usernameArr)
		
		if (usernameArr.length >= 1 
			&& usernameArr.length <=7 
			&& isSpecialChar
			&& noRepeat
			&& hasOneAlphaNum
			&& textAfterDot) {
			//if statement code
			for (int i = 0; i< usernameArr.length;i++) {
				usernameArr[i] = Character.toLowerCase(usernameArr[i]);
				if (usernameArr[i] == '-') usernameArr[i] = '_';
				
				if(!isPrefixChar(usernameArr[i]) )
					lowcaseUsername = "";
			}
			
			lowcaseUsername = String.valueOf(usernameArr);
		}
		
		//this gets returned at the end
		return lowcaseUsername;
		
	}
	
	public static boolean safePassword(String password) {
		char[] passwordArr = password.toCharArray();
		
		boolean hasOneAlphaNum = hasOneAlphaNum(passwordArr);
		boolean characterNum = (passwordArr.length >= 7 && passwordArr.length <= 15);
		boolean noRepeat = noRepeat(passwordArr);
		
		int[] counters = new int[4];
		boolean[] countersConditions = new boolean[4];
		//0-upperCase, 1-lowerCase, 2-number, 3-isSpecialChar, 4-hasOneAlphaNum, 5-right amount o characters
		
		//checking all the conditions first
		for (int i=0;i<passwordArr.length;i++) {
			if (Character.isUpperCase(passwordArr[i]))
				counters[0]++;
			if (Character.isLowerCase(passwordArr[i]))
				counters[1]++;
			if (passwordArr[i] >= '0' && passwordArr[i] <='9')
				counters[2]++;
			if (isSpecialChar(passwordArr[i],true))
				counters[3]++;
		}
		//if any counter is more than 0, the according condition is true
		for (int a=0; a<counters.length;a++) {
			if (counters[a] > 0) countersConditions[a] = true;
			else if (counters[a] < 1) countersConditions[a] = false;
		}
		
		if (countersConditions[0-3]
				&& hasOneAlphaNum
				&& noRepeat
				&& characterNum) return true;
		return false;
	}
	
	public static String[] validUsernames(String[] toCheck) {
		//initialize an array the same length as input 
		String[] sortArr = new String[toCheck.length];
		//initialize a counter, to know the amount of elements in final array
		int counterArr = 0;
		//iterating through the strings, every, string that isn't valid will automatically get converted into an empty one
		for (int i =0;i<toCheck.length;i++) {
			sortArr[i] = isUsername(toCheck[i]);
			int length = sortArr[i].length();
			if (length > 0) counterArr++;
		}
		
		String[] output = new String[counterArr];
		for(int j=0;j<output.length;j++) {
			for (int a =0;a<sortArr.length;a++) {
				if (sortArr[a].length()>0)sortArr[a]=output[j];
			}			
		}
		return output;
	}
	
	public static String[] validPasswords(String[] toCheck) {
		//initialize an array the same length as input 
		String[] sortArr = new String[toCheck.length];
		//initialize a counter, to know the amount of elements in final array
		int counterArr = 0;
		
		//iterating through the strings, every string that isnt a safe password will be eliminated
		for (int i =0;i<toCheck.length;i++) {
			if(safePassword(toCheck[i]))counterArr++;
		}
		
		String[] output = new String[counterArr];
		for(int j=0;j<output.length;j++) {
			for (int a =0;a<sortArr.length;a++) {
				if (sortArr[a].length()>0)sortArr[a]=output[j];
			}			
		}
		return output;
	}
	
}
