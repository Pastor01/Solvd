package com.solvd.projectSocialNetwork.parser;
import java.io.File;
import java.util.List;

import javax.xml.validation.Schema;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import com.solvd.projectSocialNetwork.model.Country;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxBParserCountry {

	private static final String SCHEMA_PATH="src/main/resources/DemoSchema.xsd";
	Logger log = LogManager.getLogger(JaxBParserCountry.class);

	public  Country getCountry(String path){
		Schema schema = XmlValidation.loadSchema(SCHEMA_PATH);
		Document document = XmlValidation.parseXmlDom(path);
		XmlValidation.validateXml(schema, document);
        try {
            JAXBContext context = JAXBContext.newInstance( Country.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Country c = (Country)unmarshaller.unmarshal(new File(path));
            return c;             
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            log.error("Can not get a country from the file",e);
        }
		return null;
	}
}
