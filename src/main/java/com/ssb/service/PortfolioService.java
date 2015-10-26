package com.ssb.service;

import com.ssb.domain.PortfolioDto;

public interface PortfolioService {

	/**
	 * Returns all the information of a portfolio
	 * 
	 * @param portfolio
	 *            The name of the portfolio
	 * @return
	 */
	PortfolioDto findPortfolioByName(String portfolio);
}
