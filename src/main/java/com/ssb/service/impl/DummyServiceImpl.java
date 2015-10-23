package com.ssb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.ssb.domain.Quote;
import com.ssb.service.DummyService;

@Service
public class DummyServiceImpl implements DummyService {

	private RestOperations restTemplate = new RestTemplate();
	private final static String URL_TEST_API = "http://gturnquist-quoters.cfapps.io/api/random";

	@Override
	public Quote requestApiRandom() {
		Quote quote = restTemplate.getForObject(URL_TEST_API, Quote.class);

		return quote;
	}


}
