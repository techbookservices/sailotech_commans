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
@XmlRootElement(name = "attr")
@XmlAccessorType (XmlAccessType.FIELD)
public class Attr {
	 @XmlElement(name = "name")
	private String name;
	 @XmlElement(name = "type")
	private String type;
	 @XmlElement(name = "qual")
	private String qual;
	 @XmlElement(name = "value")
	private String value;
}
