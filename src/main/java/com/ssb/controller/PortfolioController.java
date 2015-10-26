package com.ssb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssb.domain.PortfolioDto;
import com.ssb.service.PortfolioService;

@RestController
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	@RequestMapping("/portfolio/{portfolio}")
	public PortfolioDto requestPortfolio(@PathVariable String portfolio) {
		return portfolioService.findPortfolioByName(portfolio);
	}
}
