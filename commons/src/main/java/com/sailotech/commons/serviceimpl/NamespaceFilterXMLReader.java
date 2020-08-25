package com.sailotech.commons.serviceimpl;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

public class NamespaceFilterXMLReader implements XMLReader, ContentHandler {
	private XMLReader _reader;
	public NamespaceFilterXMLReader() throws SAXException,
	ParserConfigurationException {
	SAXParserFactory parserFactory;
	parserFactory = SAXParserFactory.newInstance();
	parserFactory.setNamespaceAware(true);
	// there is no point in asking a validation because
	// there is no guarantee that the document will come with
	// a proper schemaLocation.
	parserFactory.setValidating(false);
	_reader = parserFactory.newSAXParser().getXMLReader();
	}
	
	public NamespaceFilterXMLReader(ContentHandler handler) {
		// TODO Auto-generated constructor stub
	}

	public ContentHandler getContentHandler() {
		return _reader.getContentHandler();
		}

		public DTDHandler getDTDHandler() {
		return _reader.getDTDHandler();
		}

		public EntityResolver getEntityResolver() {
		return _reader.getEntityResolver();
		}

		public ErrorHandler getErrorHandler() {
		return _reader.getErrorHandler();
		}

		public boolean getFeature(String name) throws SAXNotRecognizedException,
		SAXNotSupportedException {
		return _reader.getFeature(name);
		}

		public Object getProperty(String name) throws SAXNotRecognizedException,
		SAXNotSupportedException {
		return _reader.getProperty(name);
		}

		public void parse(InputSource input) throws IOException, SAXException {
		_reader.parse(input);
		}

		public void parse(String systemId) throws IOException, SAXException {
		_reader.parse(systemId);
		}

		public void setContentHandler(ContentHandler handler) {
		// This is jaxb inserting its sax-&gt;jaxb connector
		_reader.setContentHandler(new NamespaceFilterXMLReader(handler));
		}

		public void setDTDHandler(DTDHandler handler) {
		_reader.setDTDHandler(handler);
		}

		public void setEntityResolver(EntityResolver resolver) {
		_reader.setEntityResolver(resolver);
		}

		public void setErrorHandler(ErrorHandler handler) {
		_reader.setErrorHandler(handler);
		}

		public void setFeature(String name, boolean value)
		throws SAXNotRecognizedException, SAXNotSupportedException {
		_reader.setFeature(name, value);
		}

		public void setProperty(String name, Object value)
		throws SAXNotRecognizedException, SAXNotSupportedException {
		_reader.setProperty(name, value);
		}

		@Override
		public void setDocumentLocator(Locator locator) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endPrefixMapping(String prefix) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void processingInstruction(String target, String data) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void skippedEntity(String name) throws SAXException {
			// TODO Auto-generated method stub
			
		}
}
