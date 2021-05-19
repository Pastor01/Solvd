package com.solvd.projectSocialNetwork.parser;
import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
public class XmlValidation {
	private static Logger log = LogManager.getLogger(XmlValidation.class);
	 
	 public static void validateXml(Schema schema, Document document) {
		  try {
		   // 3. Get a validator from the schema.
		   Validator validator = schema.newValidator();
		   log.info("Validator Class: " + validator.getClass().getName());

		   // validating the document against the schema
		   validator.validate(new DOMSource(document));
		   log.info("Validation passed");

		  } catch (Exception e) {
		   // catching all validation exceptions
		   log.error("Validate xml error",e);
		  }
	 }
	 
	 public static Document parseXmlDom(String xmlName) {
		  Document document = null;
		  try {
		   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   document = builder.parse(new File(xmlName));
		  } catch (Exception e) {
			log.error("Parse xml error",e);
		  }
		  return document;
	 }

		
	 public static Schema loadSchema(String schemaFileName) {
		  Schema schema = null;
		  try {
		   //// 1. Lookup a factory for the W3C XML Schema language
		   String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		   SchemaFactory factory = SchemaFactory.newInstance(language);

		   /*
		    * 2. Compile the schema.
		    */
		   schema = factory.newSchema(new File(schemaFileName));
		  } catch (Exception e) {
		   log.error("Load schema error",e);
		  }
		  return schema;
	 }
}
