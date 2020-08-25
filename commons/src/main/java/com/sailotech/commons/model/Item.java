/**
 * 
 */
package com.sailotech.commons.model;

import java.util.List;

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
@XmlRootElement(name = "item")
@XmlAccessorType (XmlAccessType.FIELD)
public class Item {
	 // @XmlElement(name = "createdBy")
	 private String createdBy;
	 // @XmlElement(name = "createdByName")
	 private String createdByName;
	 // @XmlElement(name = "createdTS")
	 private String createdTS;
	 // @XmlElement(name = "lastChangedBy")
	 private String lastChangedBy;
	 // @XmlElement(name = "lastChangedByName")
	 private String lastChangedByName;
	 // @XmlElement(name = "lastChangedTS")
	 private String lastChangedTS;
	 // @XmlElement(name = "filename")
	 private String filename;
	 // @XmlElement(name = "size")
	 private String size;
	 // @XmlElement(name = "pid")
	 private String pid;
	 // @XmlElement(name = "id")
	 private String id;
	 // @XmlElement(name = "version")
	 private String version;
	 // @XmlElement(name = "reprItem")
	 private String reprItem;
	 // @XmlElement(name = "displayName")
	 private String displayName;
	 // @XmlElement(name = "entityName")
	 private String entityName;
    @XmlElement(name = "attrs")
	private List<Attr> attrs;
   @XmlElement(name = "resrs")
  	private List<Res> resrs;
   // @XmlElement(name = "acl")
  	private Acl acl;
	
	 
}
