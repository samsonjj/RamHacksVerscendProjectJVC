import org.xml.sax.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

class fileConversion {
	BufferedReader in;
	StreamResult out;
	TransformerHandler th;

	public static void main(String args[]) {
		new fileConversion().begin();
	}

	public void begin() {
		try {
			Scanner in = new Scanner(new BufferedReader(new FileReader("20000Records.txt")));
			out = new StreamResult("data.xml");
			openXml();
			in.useDelimiter("\\^");
			String[] inputDATA = new String[76];
			if (in.hasNext()) {
				in.next();
			}
			Transformation trans1 = new Transformation(inputDATA);
			while (in.hasNext()) {
				Scanner scanRecord = new Scanner(in.next());
				scanRecord.useDelimiter("~");
				for (int i = 0; i < 76; i++) {
					if (i == 75) {
						inputDATA[i] = "null";
					} else{
						inputDATA[i] = scanRecord.next().trim();
					}
					
					// process(inputDATA[i]);
				}
				trans1.callsALLtransformations();
				for (int i = 0; i < 76; i++) {
					process(inputDATA[i]);
				}

			}

			closeXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openXml() throws ParserConfigurationException, TransformerConfigurationException, SAXException {

		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		th = tf.newTransformerHandler();

		// pretty XML output
		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");

		th.setResult(out);
		th.startDocument();
		th.startElement(null, null, "inserts", null);
	}

	public void process(String s) throws SAXException {
		String[] xmlTAGS = { "ClaimID", "RecordID", "ClaimTypeIndicator", "SupplementalType", "ClaimIndicator",
				"AdjudicationDate", "CMSContractNumber", "MemberLastName", "MemberFirstName", "MemberNameSuffix",
				"MemberAddress1", "MemberAddress2", "MemberCity", "MemberState", "MemberPostal", "MemberZip",
				"RenderingProviderNPI", "PayerResponsibiltySequenceNumber", "DateClaimPaidByOtherPayer",
				"OtherSubscriberLastName", "OtherSubscriberFirstName", "OtherSubscriberMiddleName",
				"OtherSubscriberNameSuffix", "OtherSubscriberAddress1", "OtherSubscriberAddress2",
				"OtherSubscriberCity", "OtherSubscriberState", "OtherSubscriberPostal", "OtherSubscriberZip",
				"ServiceStartDate", "ServiceEndDate" };
		for (int i = 0; i < 31; i++){
			th.startElement(null, null, xmlTAGS[i], null);
			th.characters(s.toCharArray(), 0, s.length());
			th.endElement(null, null, xmlTAGS[i]);
		}
		
	}

	public void closeXml() throws SAXException {
		th.endElement(null, null, "inserts");
		th.endDocument();
	}

}