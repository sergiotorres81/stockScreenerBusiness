package com.ssb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssb.domain.Quote;
import com.ssb.service.DummyService;

@RestController
public class DummyController {

	@Autowired
	private DummyService dummyService;

	@RequestMapping("/dummy")
	public Quote requestApiRandom() {
		return dummyService.requestApiRandom();
	}

}
