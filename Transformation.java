import java.util.Scanner;

//if it says "Institutional" or "professional" turn to I or P respectively
//204 doesn't change to PA
//if provider type is hip or hop, it still has to change to something else besides PHYS
//
public class Transformation {
	String[] inputRecords;

	public Transformation(String[] dataTo_transform) {
		inputRecords = dataTo_transform;
	}

	public void callsALLtransformations(){
		this.change_supplementalType();
		this.change_claimTypeIndicator();
		this.copyFROMmemberTOother();
		this.changeDateClaimPaid();
		this.changeStartEndDateFormat();
	}

	public void change_supplementalType() {
		if (inputRecords[3].equals("101") || inputRecords[3].equals("110") || inputRecords[3].equals("111") || inputRecords[3].equals("201")
				|| inputRecords[3].equals("205") || inputRecords[3].equals("206")) {
			inputRecords[3] = "CR";
			System.out.println("Transformation to CR complete");
		}
		if (inputRecords[3].equals("103") || inputRecords[3].equals("104") || inputRecords[3].equals("105") || inputRecords[3].equals("107")
				|| inputRecords[3].equals("108") || inputRecords[3].equals("109") || inputRecords[3].equals("112")
				|| inputRecords[3].equals("113") || inputRecords[3].equals("202") || inputRecords[3].equals("203")
				|| inputRecords[3].equals("204")) {
			inputRecords[3] = "PA";
			System.out.println("Transformation to PA complete");
		}

	}

	public void change_claimTypeIndicator(){
		if(inputRecords[2].toLowerCase().equals("institutional")) {
			inputRecords[2] = "I";
		}
		if(inputRecords[2].toLowerCase().equals("professional")) {
			inputRecords[2] = "P";
		}
		if (inputRecords[2].equals("HIP")){
			inputRecords[20] = "INPT";
			inputRecords[2] = "I";
			System.out.println("Transformation to INPT and I complete");
		} else if (inputRecords[2].equals("HOP")){
			inputRecords[20] = "OUTP";
			inputRecords[2] = "I";
			System.out.println("Transformation to OUTP and I complete");
		} else{
			inputRecords[2] = "P";
			inputRecords[20] = "PHYS";
			System.out.println("Transformation to P and PHYS complete");
		}
	}

	public void copyFROMmemberTOother(){
		if (inputRecords[32].equals("P")) {
			inputRecords[34] = inputRecords[9];
			inputRecords[35] = inputRecords[10];
			inputRecords[36] = inputRecords[11];
			inputRecords[37] = inputRecords[13];
			inputRecords[38] = inputRecords[14];
			inputRecords[39] = inputRecords[15];			
			inputRecords[40] = inputRecords[16];
			inputRecords[42] = inputRecords[17];
			inputRecords[41] = inputRecords[18];
		}
	}

	public void changeDateClaimPaid () {
		if (!inputRecords[6].isEmpty()) {
			inputRecords[33] = null;
		}
	}
	
	public void changeStartEndDateFormat() {
		Scanner dateScanner = new Scanner(inputRecords[43]);
		int year1 = 1, month1 = 1, day1 = 1;
		dateScanner.useDelimiter("/");
		if(dateScanner.hasNextInt()) {
			month1 = dateScanner.nextInt();
		}
		if(dateScanner.hasNextInt()) {
			day1 = dateScanner.nextInt();
		}
		if(dateScanner.hasNextInt()) {
			year1 = dateScanner.nextInt();
		}
		String month1s = month1 + "";
		String day1s = day1 + "";
		String year1s = year1 + "";
		if(month1s.length() == 1) month1s = "0" + month1s;
		if(day1s.length() == 1) day1s = "0" + day1s;
		inputRecords[43] = year1s + "" + month1s  + "" + day1s;
		dateScanner = new Scanner(inputRecords[44]);
		int year2 = 1, month2 = 1, day2 = 1;
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
		String month2s = month2 + "";
		String day2s = day2 + "";
		String year2s = year2 + "";
		if(month2s.length() == 1) month2s = "0" + month2s;
		if(day2s.length() == 1) day2s = "0" + day2s;

		inputRecords[44] = year2s + "" + month2s  + "" + day2s;
	}
}
