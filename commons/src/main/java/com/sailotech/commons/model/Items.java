/**
 * 
 */
package com.sailotech.commons.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author dhanunjaya.potteti
 *
 */
@Component
//@XmlRootElement(name = "items")
@XmlRootElement(name = "items")
@XmlAccessorType (XmlAccessType.FIELD)
@Data
public class Items {
	//@XmlElement(name = "searchXQuery")
	private String searchXQuery;

	
	 @XmlElement(name = "item")
	private List<Item> item;
	
   @XmlAttribute(name = "xmlns")
	 private String xmlns;

}
