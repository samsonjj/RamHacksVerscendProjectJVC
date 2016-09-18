import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Static methods on TRIMMED String[]
 */
public class Validate {

	/*public static boolean validate(String[] claimFields, String[] CONTRACT_NUMBERS) {

	}*/
	public static final Comparator<String> FIRST_WORD_COMPARATOR = new Comparator<String>() {

		public int compare(String one, String two) {
			Scanner scannerOne = new Scanner(one);
			Scanner scannerTwo = new Scanner(two);

			return scannerOne.next().compareToIgnoreCase(scannerTwo.next());
		}
	};
	public static final Comparator<String> SECOND_WORD_COMPARATOR = new Comparator<String>() {

		public int compare(String one, String two) {
			Scanner scannerOne = new Scanner(one);
			Scanner scannerTwo = new Scanner(two);

			for(int i = 0; i < 1; i++) {
				if(scannerOne.hasNext() && scannerTwo.hasNext()){
					scannerOne.next();
					scannerTwo.next();
				}
			}
			return scannerOne.next().compareToIgnoreCase(scannerTwo.next());
		}
	};
	public static final Comparator<String> THIRD_WORD_COMPARATOR = new Comparator<String>() {

		public int compare(String one, String two) {
			Scanner scannerOne = new Scanner(one);
			Scanner scannerTwo = new Scanner(two);

			for(int i = 0; i < 2; i++) {
				if(scannerOne.hasNext() && scannerTwo.hasNext()){
					scannerOne.next();
					scannerTwo.next();
				}
			}
			return scannerOne.next().compareToIgnoreCase(scannerTwo.next());
		}
	};

	private static final String[] ICD9_THIRD;
	private static final String[] ICD10_SECOND;
	private static final String[] CONTRACT_NUMBERS_SECOND;

	static {
		String[] temp = Arrays.copyOf(CodeSets.ICD9_DIAGS, CodeSets.ICD9_DIAGS.length);
		Arrays.sort(temp, THIRD_WORD_COMPARATOR);
		ICD9_THIRD = temp;
		temp = Arrays.copyOf(CodeSets.ICD10_DIAGS, CodeSets.ICD10_DIAGS.length);
		Arrays.sort(temp, SECOND_WORD_COMPARATOR);
		ICD10_SECOND = temp;
		temp = Arrays.copyOf(CodeSets.CONTRACT_NUMBERS, CodeSets.CONTRACT_NUMBERS.length);
		Arrays.sort(temp, SECOND_WORD_COMPARATOR);
		CONTRACT_NUMBERS_SECOND = temp;
	}

	public static boolean validate(String[] claimFields) {
		return(validateRule1(claimFields) && validateRule2(claimFields)
				&& validateRule3(claimFields) && validateRule4(claimFields)
				&& validateRule5(claimFields) && validateRule6(claimFields);
	}
	public static boolean validateRule1(String[] claimFields) {

		for(int codeField = 45; codeField < 82; codeField+=3) {
			if(claimFields[codeField + 1].substring(0, 1).equals("B")) {
				if(Arrays.binarySearch(ICD9_THIRD, claimFields[codeField], THIRD_WORD_COMPARATOR) >= 0) {
					return true;
				}
				/*if(Arrays.binarySearch(CodeSets.ICD9_SECOND, claimFields[codeField], SECOND_WORD_COMPARATOR) > 0) {
					return true;
				}*/
			}
			else {
				if(Arrays.binarySearch(ICD10_SECOND, claimFields[codeField], SECOND_WORD_COMPARATOR) >= 0) {
					return true;
				}
				/*if(Arrays.binarySearch(CodeSets.ICD10_DIAGS, claimFields[codeField], FIRST_WORD_COMPARATOR) > 0) {
					return true;
				}*/
			}
		}
		return false;
	}
	//This will usually return false because their test file is year last
	public static boolean validateRule2(String[] claimFields) {
		int century = 0;
		int year = 0;
		int month = 0;
		int day = 0;

		try{
			century = Integer.parseInt(claimFields[43].substring(0, 2));
			year = Integer.parseInt(claimFields[43].substring(2, 4));
			month = Integer.parseInt(claimFields[43].substring(4, 6));
			day = Integer.parseInt(claimFields[43].substring(6, 8));
		}
		catch(NumberFormatException e) {
			return false;
		}

		if(century != 19 || century != 20 || month < 1 || month > 12 || day < 1 || day > 31) {
			return false;
		}
		return true;
	}

	public static boolean validateRule3(String[] claimFields) {
		if(claimFields[3].equals("CR") || claimFields[3].equals("PA") || claimFields[3].equals("RA") || claimFields[3].equals("WV") || claimFields[3].equals("AD")) {
			return true;
		}
		else return false;
	}

	public static boolean validateRule4(String[] claimFields) {
		if(Arrays.binarySearch(CONTRACT_NUMBERS_SECOND, claimFields[7], SECOND_WORD_COMPARATOR) < 0) {
			return false;
		}
		else return true;
	}

	//transform first
	public static boolean validateRule5(String[] claimFields) {
		int year1 = 1, month1 = 1, day1 = 1;
		try{
			year1 = Integer.parseInt(claimFields[44].substring(0, 4));
			month1 = Integer.parseInt(claimFields[44].substring(4, 6));
			day1 = Integer.parseInt(claimFields[44].substring(6, 8));
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Date wrong format");
			return false;
		}
		catch(NumberFormatException e) {
			System.out.println("Date wrong format");
			return false;
		}
		if(year1 < 2004 || year1 > 2016) {
			return false;
		}
		int index = Arrays.binarySearch(CodeSets.DCP_CODES, year1 + "", FIRST_WORD_COMPARATOR);
		Scanner sc = new Scanner(CodeSets.DCP_CODES[index]);
		int year2 = 1, month2 = 1, day2 = 1;
		sc.next();
		Scanner dateScanner = new Scanner(sc.next());
		dateScanner.useDelimiter("/");
		if(dateScanner.hasNextInt()) {
			month2 = dateScanner.nextInt();
		}
		if(dateScanner.hasNextInt()) {
			day2 = dateScanner.nextInt();
		}
		if(dateScanner.hasNextInt()) {
			year2 = dateScanner.nextInt();
		}
		if(year1 < year2) return true;
		if(year1 == year2 && month1 < month2) return true;
		if(year1 == year2 && month1 == month2 && day1 < day2) return true;
		return false;
	}

	public static boolean validateRule6(String[] claimFields) {
		int year1 = 1, month1 = 1, day1 = 1;
		try{
			year1 = Integer.parseInt(claimFields[44].substring(0, 4));
			month1 = Integer.parseInt(claimFields[44].substring(4, 6));
			day1 = Integer.parseInt(claimFields[44].substring(6, 8));
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Date wrong format");
			return false;
		}
		catch(NumberFormatException e) {
			System.out.println("Date wrong format");
			return false;
		}

		for(int i = 0; i < 10; i++) 
		if(year1 < 2015){
			if(claimFields[46].substring(0, 1).equals("B")) return true;
		}
		if(year1 == 2015 && month1 < 10){
			if(claimFields[46].substring(0, 1).equals("B")) return true;
		}
		if(claimFields[46].substring(0, 1).equals("A")) return true;
		return false;

	}
}

