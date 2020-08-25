/**
 * 
 */
package com.sailotech.commons.ctrl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sailotech.commons.service.XMLModifierService;

/**
 * @author dhanunjaya.potteti
 *
 */
@RestController
@RequestMapping("/xml-datatreck")
public class XMLModifierController {
	@Autowired
	XMLModifierService xmlService;

	@PostMapping("/download")
	public void downloadFile(@RequestParam(value = "xmlFile", required = true) MultipartFile xmlFile,
			@RequestParam(value = "xlsFile", required = true) MultipartFile xlsFile,@RequestParam(value = "attrValue", required = false) String attrValue,@RequestParam(value = "filter", required = false) boolean filter, HttpServletResponse response)
			throws IOException {

		String res = xmlService.modifyXMLFile(xmlFile, xlsFile,attrValue,filter);
		response.setContentType("application/xml");
		response.setHeader("Content-Disposition", "attachment; filename=items_converted.xml");
		response.getOutputStream().write(res.getBytes());
		response.flushBuffer();
		/*
		 * InputStream inputStream = new
		 * ByteArrayInputStream(res.getBytes(Charset.forName("UTF-8")));
		 * InputStreamResource resource = new InputStreamResource(inputStream);
		 */
		
	}
	
	@PostMapping("/view")
	public String view(@RequestParam(value = "xmlFile", required = true) MultipartFile xmlFile,
			@RequestParam(value = "xlsFile", required = true) MultipartFile xlsFile)
			throws IOException {

		return xmlService.modifyXMLFile(xmlFile, xlsFile);
	
		
		
	}
}
