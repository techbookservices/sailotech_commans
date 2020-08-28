package com.sailotech.commons.model;

import lombok.Data;

@Data
public class ResponseModel {

	private String xml;
	private long itemsCount;
	private long mappingCount;
	private long replacedCount;
	private long ignoredCount;
	private long errorCount;
}
