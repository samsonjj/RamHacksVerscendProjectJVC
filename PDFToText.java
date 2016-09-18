import java.io.File;
import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.*;

public class PDFToText {
	public static void main(String[] args) {
		PDDocument pd;
		BufferedWriter wr;
		
		try{
			File input = new File("DCP_Codes.pdf");
			File output = new File("DCP_Codes.txt");
			pd = PDDocument.load(input);
			System.out.println(pd.getNumberOfPages());
			System.out.println(pd.isEncrypted());
			
			 //pd.save("CopyOfInvoice.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
	         PDFTextStripper stripper = new PDFTextStripper();
	         //stripper.setStartPage(3); //Start extracting from page 3
	         //stripper.setEndPage(5); //Extract till page 5
	         wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
	         stripper.writeText(pd, wr);
	         if (pd != null) {
	             pd.close();
	         }
	        // I use close() to flush the stream.
	        wr.close();
	 } catch (Exception e){
	         e.printStackTrace();
	        } 
	     }
	}
