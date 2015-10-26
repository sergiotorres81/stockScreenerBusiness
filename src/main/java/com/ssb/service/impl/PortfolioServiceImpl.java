package com.ssb.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssb.domain.PortfolioDto;
import com.ssb.service.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	private final static String DB_URL = "http://localhost:4040/api/portfolios/{portfolio}";
	private final static Integer DB_PORT = 4040;

	private final static String REMOTE_URL = "http://localhost";
	private final static Integer REMOTE_PORT = 5050;

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public PortfolioDto findPortfolioByName(String portfolio) {
		PortfolioDto portfolioDto = new PortfolioDto();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(DB_URL);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponents uriComponents = builder.buildAndExpand(portfolio);
		HttpEntity<PortfolioDto> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,
				entity, PortfolioDto.class);
		System.out.println(response.getBody());
		return portfolioDto;
	}

}
