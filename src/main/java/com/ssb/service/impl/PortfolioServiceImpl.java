package com.ssb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
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
import com.ssb.domain.ProductDto;
import com.ssb.domain.remoteResources.ResourceDto;
import com.ssb.service.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	private final static String DB_URL = "http://localhost:";
	private final static Integer DB_PORT = 4040;

	private final static String PORTFOLIO_URL = "/api/portfolios/{portfolio}";
	private final static String PORTFOLIO_OPERATIONS_URL = "/api/portfolios/{portfolio}/operations";

	private final static String PORTFOLIO_PRODUCTS_URL = "/api/portfolios/{portfolio}/products";

	private final static String REMOTE_URL = "http://localhost:";
	private final static Integer REMOTE_PORT = 5050;
	private final static String REMOTE_RESOURCE_URL = "/market/{market}/ticker/{ticker}";

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public PortfolioDto findPortfolioByName(String portfolio) {
		PortfolioDto portfolioDto = findPortfolio(portfolio);
		List<OperationDto> operations = findOperations(portfolio);
		portfolioDto.setOperations(operations);
		List<ResourceDto> remoteResources = findRemoteResources(portfolio);
		portfolioDto.setResources(remoteResources);
		return portfolioDto;
	}

	private List<ResourceDto> findRemoteResources(String portfolio) {
		List<ProductDto> products = findProducts(portfolio);
		List<ResourceDto> resources = new ArrayList<ResourceDto>();
		for (ProductDto product : products) {
			resources.add(findRemoteResource(product));
		}
		return resources;
	}

	private ResourceDto findRemoteResource(ProductDto product) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(REMOTE_URL + REMOTE_PORT + REMOTE_RESOURCE_URL);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(
				product.getMarket(), product.getTickerSymbol());
		HttpEntity<ResourceDto> response = restTemplate.exchange(
				uriComponents.toUri(), HttpMethod.GET, entity,
				ResourceDto.class);
		return response.getBody();
	}

	private List<ProductDto> findProducts(String portfolio) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(DB_URL
				+ DB_PORT + PORTFOLIO_PRODUCTS_URL);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(portfolio);
		ParameterizedTypeReference<List<ProductDto>> myBean = new ParameterizedTypeReference<List<ProductDto>>() {
		};

		HttpEntity<List<ProductDto>> productResponse = restTemplate
				.exchange(uriComponents.toUri(), HttpMethod.GET, entity, myBean);
		return productResponse.getBody();
	}

	private List<OperationDto> findOperations(String portfolio) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(DB_URL
				+ DB_PORT + PORTFOLIO_OPERATIONS_URL);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(portfolio);
		ParameterizedTypeReference<List<OperationDto>> myBean = new ParameterizedTypeReference<List<OperationDto>>() {
		};

		HttpEntity<List<OperationDto>> operationResponse = restTemplate
				.exchange(uriComponents.toUri(), HttpMethod.GET, entity, myBean);
		return operationResponse.getBody();
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
