/**
 * 
 */
package com.sailotech.commons.serviceimpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.sailotech.commons.model.Items;
import com.sailotech.commons.model.RequestModel;
import com.sailotech.commons.service.XMLModifierService;

/**
 * @author dhanunjaya.potteti
 *
 */
@Service
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
		Map<String, String> hm = new HashMap();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(xlsFile.getInputStream());
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
			String fileContent = new String(xmlFile.getBytes());
			 JAXBContext jContext = JAXBContext.newInstance(Items.class);
			    //creating the unmarshall object
			    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			 /*   
			    XMLReader reader = new NamespaceFilterXMLReader();
		        InputSource is = new InputSource(xmlFile.getInputStream());
		        SAXSource ss = new SAXSource(reader, is);
			    //calling the unmarshall method
		       Object obj= unmarshallerObj.unmarshal(ss);
		        System.out.println("");*/
			   Items items=(Items) unmarshallerObj.unmarshal(xmlFile.getInputStream());
			   System.out.println(items);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

}
