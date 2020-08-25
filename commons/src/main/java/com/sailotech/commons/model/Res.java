/**
 * 
 */
package com.sailotech.commons.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author dhanunjaya.potteti
 *
 */
@Component
@Data
@XmlRootElement(name = "res")
@XmlAccessorType (XmlAccessType.FIELD)
public class Res {
	 @XmlElement(name = "name")
	private String name;
@XmlElement(name = "size")
	private String size;
	@XmlElement(name = "mimetype")
	private String mimetype;
	@XmlElement(name = "filename")
	private String filename;
	 @XmlElement(name = "sha256")
	private String sha256;

}
