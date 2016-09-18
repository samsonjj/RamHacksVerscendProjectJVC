import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CodeSets {

	public final static String[] CONTRACT_NUMBERS;

	public final static String[] DCP_CODES;
	
	public final static String[] ICD10_DIAGS;
	
	public final static String[] ICD9_DIAGS;

	
	static {
		{
			String[] temp = new String[0];
			Scanner inputScanner = new Scanner("");
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("Contract_Numbers.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("Contract_Numbers not found");
			}
			int count = 0;
			while(inputScanner.hasNextLine()) {
				count++;
			}
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("Contract_Numbers.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("Contract_Numbers not found");
			}
			temp = new String[count];
			int i = 0;
			while(inputScanner.hasNextLine()) {
				temp[i] = inputScanner.nextLine().trim();
				i++;
			}
			CONTRACT_NUMBERS = temp;
		}
		{
			String[] temp = new String[0];
			Scanner inputScanner = new Scanner("");
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("ICD9_Diags.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("ICD9_Diags not found");
			}
			int count = 0;
			while(inputScanner.hasNextLine()) {
				count++;
			}
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("ICD9_Diags.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("ICD9_Diags not found");
			}
			temp = new String[count];
			int i = 0;
			while(inputScanner.hasNextLine()) {
				temp[i] = inputScanner.nextLine().trim();
				i++;
			}
			ICD9_DIAGS = temp;
		}
		{
			String[] temp = new String[0];
			Scanner inputScanner = new Scanner("");
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("ICD10_Diags.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("ICD10_Diags not found");
			}
			int count = 0;
			while(inputScanner.hasNextLine()) {
				count++;
			}
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("ICD10_Diags.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("ICD10_Diags not found");
			}
			temp = new String[count];
			int i = 0;
			while(inputScanner.hasNextLine()) {
				temp[i] = inputScanner.nextLine().trim();
				i++;
			}
			ICD10_DIAGS = temp;
		}
		{
			String[] temp = new String[0];
			Scanner inputScanner = new Scanner("");
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("DCP_Codes.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("Contract_Numbers not found");
			}
			int count = 0;
			while(inputScanner.hasNextLine()) {
				count++;
			}
			try{
				inputScanner = new Scanner(new BufferedReader(new FileReader("DCP_Codes.txt")));
			}
			catch(FileNotFoundException e) {
				System.out.println("DCP_Codes not found");
			}
			temp = new String[count];
			int i = 0;
			while(inputScanner.hasNextLine()) {
				temp[i] = inputScanner.nextLine().trim();
				i++;
			}
			DCP_CODES = temp;
		}
	}
}
