package com.ssb.service.impl;

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
import com.ssb.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	private final static String DB_URL = "http://localhost:";
	private final static Integer DB_PORT = 4040;

	private final static String PORTFOLIO_OPERATIONS_URL = "/api/portfolios/{portfolio}/operations";

	private RestTemplate restTemplate = new RestTemplate();
	@Override
	public List<OperationDto> findOperationsByPortfolioName(String porftolio) {
		return findOperations(porftolio);
	}

	private List<OperationDto> findOperations(String portfolio) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(DB_URL + DB_PORT + PORTFOLIO_OPERATIONS_URL);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(portfolio);
		ParameterizedTypeReference<List<OperationDto>> myBean = new ParameterizedTypeReference<List<OperationDto>>() {
		};

		HttpEntity<List<OperationDto>> operationResponse = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
				entity, myBean);
		return operationResponse.getBody();
	}

}
