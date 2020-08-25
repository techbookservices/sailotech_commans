/**
 * 
 */
package com.sailotech.commons.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author dhanunjaya.potteti
 *
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class EventResponse {
	private String eventStatus;
	private String eventCode;
	private String eventMessage;
	private Object eventData;
	public EventResponse(String eventStatus, String eventCode, String eventMessage, Object eventData) {
		super();
		this.eventStatus = eventStatus;
		this.eventCode = eventCode;
		this.eventMessage = eventMessage;
		this.eventData = eventData;
	}

	
	

}
