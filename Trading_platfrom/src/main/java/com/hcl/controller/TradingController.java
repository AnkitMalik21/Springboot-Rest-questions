package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.TradingDto;
import com.hcl.service.TradingService;

@RestController
@RequestMapping("/Trading/trader")
public class TradingController {
     
	@Autowired
	private TradingService service;
	
    @GetMapping("/all")
    public ResponseEntity<List<TradingDto>> getAllTrader(){
    	List<TradingDto> dto = service.getAllTrader();
    	return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    
    @GetMapping("/email")
    public ResponseEntity<TradingDto> getTraderByEmail(@RequestParam String email){
    	TradingDto dto = service.getTraderByEmail(email);
    	return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<TradingDto> updateTrader(@RequestParam String email,@RequestBody TradingDto tradingDto){
    	TradingDto dto = service.updateTrader(email ,tradingDto);
    	return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<TradingDto> createTradingDto(@RequestBody TradingDto tradingDto){
    	TradingDto dto = service.createTrader(tradingDto);
    	return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    
    
    
    
}
