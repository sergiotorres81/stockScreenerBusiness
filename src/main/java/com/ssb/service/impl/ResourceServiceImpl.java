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

import com.ssb.domain.ProductDto;
import com.ssb.domain.remoteResources.ResourceDto;
import com.ssb.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	private final static String PORTFOLIO_PRODUCTS_URL = "/api/portfolios/{portfolio}/products";

	private final static String DB_URL = "http://localhost:";
	private final static Integer DB_PORT = 4040;

	private final static String REMOTE_URL = "http://localhost:";
	private final static Integer REMOTE_PORT = 5050;
	private final static String REMOTE_RESOURCE_URL = "/market/{market}/ticker/{ticker}";

	private RestTemplate restTemplate = new RestTemplate();

	public List<ResourceDto> findResourcesByPortfolioName(String portfolio) {
		return findRemoteResources(portfolio);
	}

	private ResourceDto findRemoteResource(ProductDto product) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(REMOTE_URL + REMOTE_PORT + REMOTE_RESOURCE_URL);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(product.getMarket(), product.getTickerSymbol());
		HttpEntity<ResourceDto> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity,
				ResourceDto.class);
		return response.getBody();
	}

	private List<ProductDto> findProducts(String portfolio) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(DB_URL + DB_PORT + PORTFOLIO_PRODUCTS_URL);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(portfolio);
		ParameterizedTypeReference<List<ProductDto>> myBean = new ParameterizedTypeReference<List<ProductDto>>() {
		};

		HttpEntity<List<ProductDto>> productResponse = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
				entity, myBean);
		return productResponse.getBody();
	}

	private List<ResourceDto> findRemoteResources(String portfolio) {
		List<ProductDto> products = findProducts(portfolio);
		List<ResourceDto> resources = new ArrayList<ResourceDto>();
		for (ProductDto product : products) {
			resources.add(findRemoteResource(product));
		}
		return resources;
	}

}
