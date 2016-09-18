
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
	}
	
	public void change_supplementalType() {
		if (inputRecords[3] == "101" || inputRecords[3] == "110" || inputRecords[3] == "111" || inputRecords[3] == "201"
				|| inputRecords[3] == "205" || inputRecords[3] == "206") {
			inputRecords[3] = "CR";
//			System.out.println("Transformation to CR complete");
		}
		if (inputRecords[3] == "103" || inputRecords[3] == "104" || inputRecords[3] == "105" || inputRecords[3] == "107"
				|| inputRecords[3] == "108" || inputRecords[3] == "109" || inputRecords[3] == "112"
				|| inputRecords[3] == "113" || inputRecords[3] == "202" || inputRecords[3] == "203"
				|| inputRecords[3] == "204") {
			inputRecords[3] = "PA";
//			System.out.println("Transformation to PA complete");
		}

	}
	
	public void change_claimTypeIndicator(){
		if (inputRecords[2] == "HIP"){
			inputRecords[20] = "INPT";
			inputRecords[2] = "I";
//			System.out.println("Transformation to INPT and I complete");
		} else if (inputRecords[2] == "HOP"){
			inputRecords[20] = "OUTP";
			inputRecords[2] = "I";
//			System.out.println("Transformation to OUTP and I complete");
		} else{
			inputRecords[2] = "P";
			inputRecords[20] = "PHYS";
//			System.out.println("Transformation to P and PHYS complete");
		}
	}
	
	public void copyFROMmemberTOother(){
		if (inputRecords[32] == "P") {
			inputRecords[34] = inputRecords[9];
			inputRecords[35] = inputRecords[10];
			inputRecords[36] = inputRecords[11];
			inputRecords[76] = inputRecords[12];
			inputRecords[37] = inputRecords[13];
			inputRecords[38] = inputRecords[14];
			inputRecords[39] = inputRecords[15];			
			inputRecords[40] = inputRecords[16];
			inputRecords[42] = inputRecords[17];
			inputRecords[41] = inputRecords[18];
//			System.out.println("copy from member to other sub");
		}
	}
	
	public void changeDateClaimPaid () {
		if (!inputRecords[6].isEmpty()) {
			inputRecords[33] = "null";
//			System.out.println("change date claim paid");
		}
	}
}
