package com.hcl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.dto.TradingDto;

@Service
public interface TradingService {

	List<TradingDto> getAllTrader();

	TradingDto getTraderByEmail(String email);

	TradingDto updateTrader(String email, TradingDto tradingDto);

	TradingDto createTrader(TradingDto tradingDto);

}
