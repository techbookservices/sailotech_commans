/**
 * 
 */
package com.sailotech.commons.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sailotech.commons.model.ResponseModel;

/**
 * @author dhanunjaya.potteti
 *
 */
@Service
public interface XMLModifierService {

	String modifyXMLFile(MultipartFile xmlFile, MultipartFile xlsFile);

	ResponseModel modifyXMLFile(MultipartFile xmlFile, MultipartFile xlsFile, String attrValue, boolean filter);

	
}
