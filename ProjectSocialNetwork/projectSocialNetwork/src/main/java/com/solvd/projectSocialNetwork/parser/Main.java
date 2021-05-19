package com.solvd.projectSocialNetwork.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import com.solvd.projectSocialNetwork.model.Country;


public class Main {

	private static Logger log = LogManager.getLogger(Main.class);
	private static final String PATH_XML = "src/main/resources/Country.xml";
	
	
	public static void main(String[] args) {
		
		
		log.info("Starting parsing with SAX: ");
		try {
			
			SAXParserFactory saxParser= SAXParserFactory.newInstance();
			SAXParser parser= saxParser.newSAXParser();
			SaxParser handler = new SaxParser();
			parser.parse(new File(PATH_XML), handler);
			log.info(handler.getCountries().get(0).toString());
		} catch (SAXException | IOException | ParserConfigurationException e) {
			
			log.error("Exception throw: ",e);
		}
		
		
		log.info("Starting parsing with JAXB: ");
		    
			JaxBParserCountry pc = new JaxBParserCountry();
			Country c = pc.getCountry(PATH_XML);
			log.info(c.toString());
        
        
	
		
		
		
		
		
	}
}
