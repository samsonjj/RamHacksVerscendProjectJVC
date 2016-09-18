import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Static methods on String[]
 */
public class Validate {

	public boolean validate(String[] claimFields, String[] CONTRACT_NUMBERS) {
		
	}

	public boolean validateRule1(String[] claimFields) {

		for(int codeField = 45; codeField < 82; codeField+=3) {
			if(claimFields[codeField + 1].substring(0, 1).equals("B")) {
				if(Arrays.binarySearch(CodeSets.ICD9_DIAGS, claimFields[codeField], ICD_NO_DEC_COMPARATOR) > 0) {
					return true;
				}
			}
			else {
				if(Arrays.binarySearch(CodeSets.ICD10_DIAGS, claimFields[codeField], ICD_NO_DEC_COMPARATOR) > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public static final Comparator<String> ICD_NO_DEC_COMPARATOR = new Comparator<String>() {

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

}
