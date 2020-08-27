/**
 * 
 */
package com.sailotech.commons.serviceimpl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sailotech.commons.model.RequestModel;
import com.sailotech.commons.service.XMLModifierService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dhanunjaya.potteti
 *
 */
@Service
@Slf4j
public class XMLModifierServiceImpl implements XMLModifierService {

	@Override
	public String modifyXMLFile(MultipartFile xmlFile, MultipartFile xlsFile) {
		Map<String, String> mappings = extractMappings(xlsFile);
		try {

			String fileContent = new String(xmlFile.getBytes());
			
			 JAXBContext jContext = JAXBContext.newInstance(RequestModel.class);
			    //creating the unmarshall object
			    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			    //calling the unmarshall method
			    RequestModel student=(RequestModel) unmarshallerObj.unmarshal(xmlFile.getInputStream());


		/*	for (Map.Entry<String, String> e : mappings.entrySet()) {
				System.out.println(e.getKey() + ":" + e.getValue());
				fileContent = fileContent.replaceAll(e.getKey(), e.getValue());
			}
			// RequestModel requestModel = (RequestModel)
			// un.unmarshal(xmlFile.getInputStream());
*/			System.out.println(fileContent);
			return fileContent;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	private Map<String, String> extractMappings(MultipartFile xlsFile) {
		Map<String, String> hm = new HashMap<>();
		try (XSSFWorkbook workbook = new XSSFWorkbook(xlsFile.getInputStream())){
			XSSFSheet worksheet = workbook.getSheetAt(0);

			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

				XSSFRow row = worksheet.getRow(i);
				hm.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());

			}
			System.out.println(hm);
			return hm;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hm;

	}

	@Override
	public String modifyXMLFile(MultipartFile xmlFile, MultipartFile xlsFile, String attrValue, boolean filter) {
		Map<String, String> mappings = extractMappings(xlsFile);
		try {

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse(xmlFile.getInputStream());

			NodeList itemList = document.getElementsByTagName("item");

			System.out.println("Records in file :" + itemList.getLength());
			System.out.println("Records in Mapping file :" + mappings.size());
			String[] mappingKeys = mappings.keySet().toArray(new String[mappings.keySet().size()]);
			int replacedCount = 0;
			// remove element in loop case exception. by decrement for loop it will solve
			// the problem.
			for (int i = itemList.getLength() - 1; i >= 0; i--) {// items
				Node item = itemList.item(i);// item
				boolean replaced = false;
				if (StringUtils.containsAny(item.getTextContent(), mappingKeys)) {
					for (int j = 0; j < item.getChildNodes().getLength(); j++) {// item fields
						Node attr = item.getChildNodes().item(j);
						// if attrValue is not empty ignore other fields
						if (StringUtils.isNotBlank(attrValue) && !StringUtils.equals(attr.getNodeName(), "attrs")) {
							continue;
						}
						if (StringUtils.equalsAnyIgnoreCase(attr.getNodeName(), "attrs", "resrs", "acl")) {
							for (int k = 0; k < attr.getChildNodes().getLength(); k++) {//attrs|resrs|acl list object
								Node l2Node = attr.getChildNodes().item(k);
								if(StringUtils.equals(attr.getNodeName(), "acl")) {
									replaced |= replaceContent(mappings, mappingKeys, replaced, l2Node);
								}else {
									boolean replaceValueOnly = StringUtils.isNotBlank(attrValue) && StringUtils.equals(attr.getNodeName(), "attrs")
											&& StringUtils.equals(l2Node.getTextContent(),attrValue);
										
									
									for (int l = 0; l < l2Node.getChildNodes().getLength(); l++) {
										Node l3Node = l2Node.getChildNodes().item(l);//attr|resr one element from array
										//if replace value true then ignore other fields from element
										if(replaceValueOnly && !StringUtils.equals(l3Node.getNodeName(),"value")) {
											continue;
										}
										replaced |= replaceContent(mappings, mappingKeys, replaced, l3Node);
									}
								}
								System.out.println(l2Node);

							}
						} else {
							replaced |= replaceContent(mappings, mappingKeys, replaced, attr);
						}

					}
				}

				log.debug("each item {} {}", i, item.getTextContent());
				if (replaced) {// replaced then increase count
					replacedCount++;
				} else if (filter) {// not replaced and filter true then remove item
					item.getParentNode().removeChild(item);
				}
			}

			System.out.println("repalced Count "+replacedCount);
			System.out.println("ignored count "+(itemList.getLength()-replacedCount));
			
			return toString(document);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	private boolean replaceContent(Map<String, String> mappings, String[] mappingKeys, boolean replaced, Node node) {
		if (node.hasChildNodes() && StringUtils.containsAny(node.getChildNodes().item(0).getNodeValue(), mappingKeys)) {
			String replacedValue = replaceString(node.getChildNodes().item(0).getNodeValue(), mappingKeys, mappings);
			if (!StringUtils.equals(replacedValue, node.getChildNodes().item(0).getNodeValue())) {
				node.getChildNodes().item(0).setNodeValue(replacedValue);
				replaced = true;
			}
		}
		return replaced;
	}

	private String toString(Document document) {
		try {
			DOMSource domSource = new DOMSource(document);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private String replaceString(String input, String[] mappingKeys, Map<String, String> mappings) {
	
		for (int i = 0; i < mappingKeys.length; i++) {
			if(StringUtils.contains(input, mappingKeys[i])) {
				input = StringUtils.replace(input, mappingKeys[i], mappings.get(mappingKeys[i]));
			}
		}
		
		return input;
	}
}
