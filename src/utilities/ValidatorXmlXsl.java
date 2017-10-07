package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import javax.print.DocFlavor.URL;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidatorXmlXsl {

	private static final String XML_PATH = "/home/bilalam/git/GenericMongo/resources/mapping.xml";
	private static final String XSD_PATH = "/home/bilalam/git/GenericMongo/resources/mapping.xsd";

	public static void main(String[] args) {

		ValidatorXmlXsl val = new ValidatorXmlXsl();
		boolean valid = val.validate(XML_PATH, XSD_PATH);

		System.out.println(valid);
	}

	private static boolean validate(String xmlFile, String schemaFile) {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(new File(schemaFile));

			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlFile)));
			return true;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
