package com.ssb.service;

import java.util.List;

import com.ssb.domain.remoteResources.ResourceDto;

public interface ResourceService {

	List<ResourceDto> findResourcesByPortfolioName(String portfolio);

}
