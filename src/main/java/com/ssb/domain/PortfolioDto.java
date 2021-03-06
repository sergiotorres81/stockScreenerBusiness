package com.ssb.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssb.domain.remoteResources.ResourceDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PortfolioDto {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("visibility")
	private String visibility;

	private List<OperationDto> operations;

	private List<ResourceDto> resources;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the visibility
	 */
	public String getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility
	 *            the visibility to set
	 */
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public List<OperationDto> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationDto> operations) {
		this.operations = operations;
	}

	/**
	 * @return the resources
	 */
	public List<ResourceDto> getResources() {
		return resources;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	public void setResources(List<ResourceDto> resources) {
		this.resources = resources;
	}

}
