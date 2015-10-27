package com.ssb.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationDto {
	@JsonProperty("operationDate")
	private Date operationDate;
	@JsonProperty("price")
	private Double price;

	/**
	 * @return the operationDate
	 */
	public Date getOperationDate() {
		return operationDate;
	}

	/**
	 * @param operationDate
	 *            the operationDate to set
	 */
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

}
