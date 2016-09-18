import org.xml.sax.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

class fileConversion {
	BufferedReader in;
	StreamResult out;
	TransformerHandler th;
	long d = 0;
	long c = 0;
	int  counter = 0;
	static long startTime = 0;  
	public static void main(String args[]) {
		startTime = System.currentTimeMillis();
		new fileConversion().begin();
	}

	public void begin() {
		try {
			Scanner in = new Scanner(new BufferedReader(new FileReader("20000Records.txt")));
			out = new StreamResult("data.xml");
			openXml();
			in.useDelimiter("\\^");
			String[] inputDATA = new String[77];
			if (in.hasNext()) {
				in.next();
			}
			int counter = 0;
			String[] orderedOutput = new String[63];
			Transformation trans1 = new Transformation(inputDATA);
			while (in.hasNext()) {
				Scanner scanRecord = new Scanner(in.next());
				scanRecord.useDelimiter("~");
				// adds elements from txt to array
				for (int i = 0; i < 75; i++) {
					inputDATA[i] = scanRecord.next().trim();
//					System.out.println(inputDATA[i]);

					if (!in.hasNext()) {
						break;
					}
				}
				inputDATA[76] = "null";
//				System.out.println("counter = " + counter);
				trans1.callsALLtransformations();
//				if(!Validate.validate(inputDATA)){
//					
//				}
//					System.out.println("counter is" + counter);
//				if (counter >100){
//					break;
//				}
				
				// creating array with correct order
				orderedOutput[0] = inputDATA[5];
				for (int j = 1; j < 5; j++) {
					orderedOutput[j] = inputDATA[j];
				}
				for (int k = 5; k < 7; k++) {
					orderedOutput[k] = inputDATA[k + 1];
				}
				for (int l = 7; l < 17; l++) {
					orderedOutput[l] = inputDATA[l + 2];
				}
				for (int m = 17; m < 23; m++) {
					orderedOutput[m] = inputDATA[m + 14];
				}
				orderedOutput[23] = inputDATA[75];
				for (int n = 24; n < 32; n++) {
					orderedOutput[n] = inputDATA[n + 13];
				}
				orderedOutput[32] = "null";
				for (int p = 33; p < 63; p++) {
					orderedOutput[p] = inputDATA[p + 12];
//					System.out.println(orderedOutput[p]);
				}
//				System.out.println("gets heree");
				// for (int o = 0; o < 39; o++) {
				// System.out.println(orderedOutput[o]);
				// }
				for (int i = 0; i < 62; i++) {
					if (orderedOutput[i] == null) {
						orderedOutput[i] = "null";
					}
				}
				
				// System.out.println("prints " + orderedOutput[i] + "to xml");

			process(orderedOutput);
//			printSpeed();
//			counter++;
			}
			System.out.println("counter is" + counter);
			closeXml();
			final long endTime = System.currentTimeMillis();

			System.out.println("Total execution time: " + (endTime - startTime) + "miliseconds." );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void openXml() throws ParserConfigurationException, TransformerConfigurationException, SAXException {

		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		th = tf.newTransformerHandler();

		// pretty XML output
		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "0");
		serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty("{http://xml.apache.org/xalan}line-separator" , "\n");

		th.setResult(out);
		th.startDocument();
		th.startElement(null, null, "Client", null);
		th.startElement(null, null, "ClientID", null);
		th.startElement(null, null, "Claims", null);
	}

	public void process(String[] s) throws SAXException, IOException {
		String[] xmlTAGS = { "ClaimID", "RecordID", "ClaimTypeIndicator", "SupplementalType", "ClaimIndicator",
				"AdjudicationDate", "CMSContractNumber", "MemberLastName", "MemberFirstName", "MemberNameSuffix",
				"MemberAddress1", "MemberAddress2", "MemberCity", "MemberState", "MemberPostal", "MemberZip",
				"RenderingProviderNPI", "PayerResponsibiltySequenceNumber", "DateClaimPaidByOtherPayer",
				"OtherSubscriberLastName", "OtherSubscriberFirstName", "OtherSubscriberMiddleName",
				"OtherSubscriberNameSuffix", "OtherSubscriberAddress1", "OtherSubscriberAddress2",
				"OtherSubscriberCity", "OtherSubscriberState", "OtherSubscriberPostal", "OtherSubscriberZip",
				"ServiceStartDate", "ServiceEndDate", "Code", "Qualifier", "DeleteFlag" };
//		th.startElement(null, null, "ClientID", null);
//		th.startElement(null, null, "Claims", null);
		int i = 0;
		
		while (i < 31) {
			if (i == 0) {
				th.startElement(null, null, "Claim", null);
			}
			th.startElement(null, null, xmlTAGS[i], null);
			th.characters(s[i].toCharArray(), 0, s[i].length());
			th.endElement(null, null, xmlTAGS[i]);

			i++;
		}
		th.startElement(null, null, "DiagnosisTypes", null);
		th.startElement(null, null, "DiagnosisType", null);
		for (int k = 0; k < 32; k++) {
			if (k == 31) {
				th.startElement(null, null, "DiagnosisTypeID", null);
				th.characters(s[i].toCharArray(), 0, s[i].length());
				th.endElement(null, null, "DiagnosisTypeID");
			}
		}
		th.startElement(null, null, "Diagnoses", null);
		int a = 32;
		while (a < 61) {
			th.startElement(null, null, "Diagnosis", null);
			
			th.startElement(null, null, xmlTAGS[31], null);
			th.characters(s[a].toCharArray(), 0, s[a].length());
			th.endElement(null, null, xmlTAGS[31]);
			
			th.startElement(null, null, xmlTAGS[32], null);
			th.characters(s[a+1].toCharArray(), 0, s[a+1].length());
			th.endElement(null, null, xmlTAGS[32]);
			
			th.startElement(null, null, xmlTAGS[33], null);
			th.characters(s[a+2].toCharArray(), 0, s[a+2].length());
			th.endElement(null, null, xmlTAGS[33]);
			
			th.endElement(null, null, "Diagnosis");
			a = a+3;
		}
		th.endElement(null, null, "Claim");
//		th.endElement(null, null, "Claims");
//		th.endElement(null, null, "ClientID");
//		for (int n = 32; n < 63;) {
//			th.startElement(null, null, "Diagnosis", null);
//			
//			th.startElement(null, null, "Code", null);
//			th.characters(s[n].toCharArray(), 0, s[n].length());
//			th.endElement(null, null, "Code");
//			n++;
//			th.startElement(null, null, "Qualifier", null);
//			th.characters(s[n+1].toCharArray(), 0, s[n+1].length());
//			th.endElement(null, null, "Qualifier");
//			n++;
//			th.startElement(null, null, "DeleteFlag", null);
//			th.characters(s[n+2].toCharArray(), 0, s[n+2].length());
//			th.endElement(null, null, "DeleteFlag");
//			n++;
//			th.endElement(null, null, "Diagnosis");
//		}

	}

	public void closeXml() throws SAXException {
		th.endElement(null, null, "Claims");
		th.endElement(null, null, "ClientID");
		th.endElement(null, null, "Client");
		th.endDocument();
	}
	public void printSpeed() {
		if(counter % 10000 == 0) {
			counter++;
			counter = 0;
			System.out.println("Speed: " + 10000.0 * 1000 / (c - d) + "claims per second");
			d = c;
			c = System.currentTimeMillis();
		}
	}
	

}