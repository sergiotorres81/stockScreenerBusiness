package com.ssb.service;

import java.util.List;

import com.ssb.domain.OperationDto;

public interface OperationService {

	List<OperationDto> findOperationsByPortfolioName(String porftolio);
}
