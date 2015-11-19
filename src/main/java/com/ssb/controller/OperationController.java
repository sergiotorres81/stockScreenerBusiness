package com.ssb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssb.domain.OperationDto;
import com.ssb.service.OperationService;

@RestController
public class OperationController {

	@Autowired
	private OperationService operationService;

	@RequestMapping("/portfolio/{portfolio}/operations")
	public List<OperationDto> requestOperationsByPortfolio(@PathVariable String portfolio) {

		return operationService.findOperationsByPortfolioName(portfolio);
	}

}
