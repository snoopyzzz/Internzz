package com.zz.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7877343181367762729L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
