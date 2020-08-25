/**
 * 
 */
package com.sailotech.commons.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author dhanunjaya.potteti
 *
 */
@Data
@Component
@XmlRootElement(name = "acl")
public class Acl {
	// @XmlElement(name = "id")
	 private String id;
	// @XmlElement(name = "name")
	 private String name;
}
