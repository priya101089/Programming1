
public class Validator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello world")
	}

	//creating a dummy method
	public static boolean isAlphaNum(char inputChar) {
		boolean isAlphaNum = ( Character.isLetter(inputChar) || Character.isDigit(inputChar));
		return isAlphaNum;
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
} 
//comments to come 
