package com.hcl.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.TradingDto;
import com.hcl.entity.TradingEntity;
import com.hcl.repository.TradingRepository;

@Service
public class TradingServiceImpl implements TradingService {

	@Autowired
	private TradingRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<TradingDto> getAllTrader() {
        List<TradingEntity> list = repo.findAll();
        List<TradingDto> listDto = list.stream()
        		.map(li -> modelMapper.map(li,TradingDto.class))
        	    .toList();
        return listDto;
	}

	

	@Override
	public TradingDto updateTrader(String email, TradingDto tradingDto) {
	    TradingEntity entity = repo.findByEmail(email)
	        .orElseThrow(() -> new RuntimeException("User not found"));
	    
	    System.out.println("Before update: " + entity.getUpdatedAt());
	    
	    // Update fields
	    modelMapper.map(tradingDto, entity);
	    
	    // Save and check
	    TradingEntity savedEntity = repo.save(entity);
	    System.out.println("After update: " + savedEntity.getUpdatedAt());
	    
	    return modelMapper.map(savedEntity, TradingDto.class);
	}
  

	@Override
	public TradingDto createTrader(TradingDto tradingDto) {
	    TradingEntity entity = modelMapper.map(tradingDto, TradingEntity.class);
	    TradingEntity savedEntity = repo.save(entity);  
	    return modelMapper.map(savedEntity, TradingDto.class);  
	}




	@Override
	public TradingDto getTraderByEmail(String email) {
		TradingEntity entity = repo.findByEmail(email)
				.orElseThrow(()-> new RuntimeException(""));
		return modelMapper.map(entity,TradingDto.class);
	}





}
