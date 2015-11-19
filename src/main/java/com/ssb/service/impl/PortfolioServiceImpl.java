package com.ssb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssb.domain.OperationDto;
import com.ssb.domain.PortfolioDto;
import com.ssb.domain.remoteResources.ResourceDto;
import com.ssb.service.OperationService;
import com.ssb.service.PortfolioService;
import com.ssb.service.ResourceService;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	private final static String DB_URL = "http://localhost:";
	private final static Integer DB_PORT = 4040;

	private final static String PORTFOLIO_URL = "/api/portfolios/{portfolio}";


	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private OperationService operationService;
	@Autowired
	private ResourceService resourceService;

	@Override
	public PortfolioDto findPortfolioByName(String portfolio) {
		PortfolioDto portfolioDto = findPortfolio(portfolio);
		List<OperationDto> operations = operationService.findOperationsByPortfolioName(portfolio);
		portfolioDto.setOperations(operations);
		List<ResourceDto> remoteResources = resourceService.findResourcesByPortfolioName(portfolio);
		portfolioDto.setResources(remoteResources);
		return portfolioDto;
	}

	private PortfolioDto findPortfolio(String portfolio) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(DB_URL
				+ DB_PORT + PORTFOLIO_URL);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(portfolio);
		HttpEntity<PortfolioDto> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
				entity, PortfolioDto.class);
		return response.getBody();
	}

}
