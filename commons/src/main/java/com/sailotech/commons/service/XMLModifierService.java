/**
 * 
 */
package com.sailotech.commons.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dhanunjaya.potteti
 *
 */
@Service
public interface XMLModifierService {

	String modifyXMLFile(MultipartFile xmlFile, MultipartFile xlsFile);

	String modifyXMLFile(MultipartFile xmlFile, MultipartFile xlsFile, String attrValue, boolean filter);

	
}
