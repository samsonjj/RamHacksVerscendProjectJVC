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

			return scannerOne.next().compareToIgnoreCase(two);
		}
	};
	public static final Comparator<String> SECOND_WORD_COMPARATOR = new Comparator<String>() {

		public int compare(String one, String two) {
			System.out.println("Compare");

			Scanner scannerOne = new Scanner(one);
			System.out.println(one + "|" + two);

			for(int i = 0; i < 1; i++) {
				if(scannerOne.hasNext()){
					scannerOne.next();
				}
			}

			return scannerOne.next().compareToIgnoreCase(two);
		}
	};
	/*public static final Comparator<String> THIRD_WORD_COMPARATOR = new Comparator<String>() {

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
	};*/

	//private static final String[] ICD9_THIRD;
	//private static final String[] ICD10_SECOND;
	//private static final String[] CONTRACT_NUMBERS_SECOND;

	static {
		System.out.println("hello");
		/*String[] temp = Arrays.copyOf(CodeSets.ICD9_DIAGS, CodeSets.ICD9_DIAGS.length);
		Arrays.sort(temp, THIRD_WORD_COMPARATOR);
		ICD9_THIRD = temp;*/
		System.out.println("HIIIII");
		/*temp = Arrays.copyOf(CodeSets.ICD10_DIAGS, CodeSets.ICD10_DIAGS.length);
		Arrays.sort(temp, SECOND_WORD_COMPARATOR);
		ICD10_SECOND = temp;*/
		/*String[] temp = Arrays.copyOf(CodeSets.CONTRACT_NUMBERS, CodeSets.CONTRACT_NUMBERS.length);
		System.out.println("watup");
		Arrays.sort(temp, SECOND_WORD_COMPARATOR);
		CONTRACT_NUMBERS_SECOND = temp;*/
		System.out.println("Static done");
	}

	public static boolean validate(String[] claimFields) {
		if(validateRule1(claimFields)) {
			System.out.println("1success");
		}
		else {
			System.out.println("1fail");
			return false;
		}
		if(validateRule2(claimFields)) {
			System.out.println("2success");
		}
		else {
			System.out.println("2fail");
			return false;
		}
		if(validateRule3(claimFields)) {
			System.out.println("3success");
		}
		else {
			System.out.println("3fail");
			return false;
		}
		if(validateRule4(claimFields)) {
			System.out.println("4success");
		}
		else {
			System.out.println("4fail");
			return false;
		}
		if(validateRule5(claimFields)) {
			System.out.println("5success");
		}
		else {
			System.out.println("5fail");
			return false;
		}
		if(validateRule6(claimFields)) {
			System.out.println("6success");
		}
		else {
			System.out.println("6fail");
			return false;
		}
		return true;

	}
	public static boolean validateRule1(String[] claimFields) {

		for(int codeField = 45; codeField < 72; codeField+=3) {
			System.out.println("Val 12 start");
			if(claimFields[codeField + 1].substring(0, 1).equals("B")) {
				System.out.println("HO");
				if(Arrays.binarySearch(CodeSets.ICD9_DIAGS, claimFields[codeField], SECOND_WORD_COMPARATOR) >= 0) {
					return true;
				}
				System.out.println("Hell");
				for(int i = 0; i < claimFields[codeField].length() - 1; i++) {
					if(Arrays.binarySearch(CodeSets.ICD9_DIAGS, claimFields[codeField].substring(0, i + 1) + "." + claimFields[codeField].substring(i + 1, claimFields[codeField].length()), SECOND_WORD_COMPARATOR) >= 0) {
						return true;
					}
				}
				/*if(Arrays.binarySearch(CodeSets.ICD9_SECOND, claimFields[codeField], SECOND_WORD_COMPARATOR) > 0) {
					return true;
				}*/
			}
			else {
				if(Arrays.binarySearch(CodeSets.ICD10_DIAGS, claimFields[codeField], FIRST_WORD_COMPARATOR) >= 0) {
					return true;
				}
				for(int i = 0; i < claimFields[codeField].length() - 1; i++) {
					if(Arrays.binarySearch(CodeSets.ICD10_DIAGS, claimFields[codeField].substring(i, i + 1) + "." + claimFields[codeField].substring(i + 1, claimFields[codeField].length()), FIRST_WORD_COMPARATOR) >= 0) {
						return true;
					}
				}
				/*if(Arrays.binarySearch(CodeSets.ICD10_DIAGS, claimFields[codeField], FIRST_WORD_COMPARATOR) > 0) {
					return true;
				}*/
			}
		}
		return false;
	}
	/*public static boolean validateRule1(String[] claimFields) {

		for(int codeField = 45; codeField < 82; codeField+=3) {
			System.out.println("Val 1 start");
			if(claimFields[codeField + 1].substring(0, 1).equals("B")) {
				if(Arrays.binarySearch(ICD9_THIRD, claimFields[codeField], THIRD_WORD_COMPARATOR) >= 0) {
					return true;
				}
				if(Arrays.binarySearch(CodeSets.ICD9_SECOND, claimFields[codeField], SECOND_WORD_COMPARATOR) > 0) {
					return true;
				}
			}
			else {
				if(Arrays.binarySearch(ICD10_SECOND, claimFields[codeField], SECOND_WORD_COMPARATOR) >= 0) {
					return true;
				}
				if(Arrays.binarySearch(CodeSets.ICD10_DIAGS, claimFields[codeField], FIRST_WORD_COMPARATOR) > 0) {
					return true;
				}
			}
		}
		return false;
	}*/
	//This will usually return false because their test file is year last
	public static boolean validateRule2(String[] claimFields) {
		System.out.println("Val 2 start");

		int century = 0;
		int year = 0;
		int month = 0;
		int day = 0;

		System.out.println(claimFields[43]);

		try{
			century = Integer.parseInt(claimFields[43].substring(0, 2));
			System.out.println(century);
			year = Integer.parseInt(claimFields[43].substring(2, 4));
			month = Integer.parseInt(claimFields[43].substring(4, 6));
			day = Integer.parseInt(claimFields[43].substring(6, 8));
		}
		catch(NumberFormatException e) {
			System.out.println("Number format exception");
			return false;
		}

		if((century != 19 && century != 20) || month < 1 || month > 12 || day < 1 || day > 31) {
			System.out.println("not valid date");
			return false;
		}
		return true;
	}

	public static boolean validateRule3(String[] claimFields) {
		System.out.println("Val 3 start");

		if(claimFields[3].equals("CR") || claimFields[3].equals("PA") || claimFields[3].equals("RA") || claimFields[3].equals("WV") || claimFields[3].equals("AD")) {
			return true;
		}
		else return false;
	}

	public static boolean validateRule4(String[] claimFields) {
		if(search(CodeSets.CONTRACT_NUMBERS, claimFields[7], SECOND_WORD_COMPARATOR) < 0) {
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
		System.out.println(year1);
		int index = Arrays.binarySearch(CodeSets.DCP_CODES, year1 + "", FIRST_WORD_COMPARATOR);
		if(index < 0) return false;
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

		for(int i = 0; i < 10; i++) {
			if(year1 < 2015){
				if(!claimFields[46 + i * 3].substring(0, 1).equals("B"))
				{
					return false;
				}
			}
			else if(year1 == 2015 && month1 < 10){
				if(!claimFields[46 + i * 3].substring(0, 1).equals("B")) return false;
			}
			else if(!claimFields[46 + i * 3].substring(0, 1).equals("A")) return false;
		}
		return true;
	}

	public static int binarySearch(String[] a, String key, Comparator<String> c) {
		int start = 1;
		int end = a.length;
		while(end - start > 0) {
			Scanner sc = new Scanner(a[((start + end) / 2)]);
			sc.next();
			String look = sc.next();
			System.out.println(look);
			if(c.compare(key, (a[((start + end) / 2)])) == 0) {
				return (start + end) / 2;
			}
			else if(c.compare(key, a[((start + end) / 2)]) < 0) {
				end = (end + start) / 2 - 1;
			}
			else if(c.compare(key, a[((start + end) / 2)]) > 0) {
				start = (end + start) / 2 + 1;
			}
		}
		return -1;
	}

	public static int search(String[] a, String key, Comparator<String> c) {
		for(int i = 1; i < a.length; i++) {
			if(c.compare(a[i], key) == 0) {
				return i;
			}
		}
		return -1;
	}
}

