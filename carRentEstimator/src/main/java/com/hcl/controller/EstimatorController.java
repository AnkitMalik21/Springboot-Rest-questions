package com.hcl.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.model.CabRentBean;
import com.hcl.service.CabRentService;

import jakarta.validation.Valid;

@Controller
public class EstimatorController {
    
	@Autowired
	private CabRentService cabRentService;
	
	@RequestMapping(value = "/estimatorpage",method = RequestMethod.GET)
	public String estimator(@ModelAttribute("cab") CabRentBean cabRentBean,BindingResult result,ModelMap model) {
		return "estimatorpage";
	}
	
	@RequestMapping(value ="/estimatorDesk" ,method=RequestMethod.POST)
	public String calculateCabRent(@Valid @ModelAttribute("cab") CabRentBean cabRentBean,BindingResult result,ModelMap model) {
		if(result.hasErrors()) {
			return "estimatorpage";
		}
		
		double totalFare = cabRentService.calculateCabRent(cabRentBean);
		model.addAttribute("message","Estimated fare " + totalFare);
		return "estimatordesk";
	}
	
	@ModelAttribute("cabList")
	public Map<String,String> buildState(){
		Map<String,String>cabList = new LinkedHashMap<>();
		cabList.put("Micro-NonAc","Micro-NonAc");
		cabList.put("Micro-Ac","Micro-Ac");
		cabList.put("Mini-NonAc","Mini-NonAc");
		cabList.put("Mini-Ac","Mini-Ac");
		cabList.put("Sedan-Ac","Sedan-NonAc");
		cabList.put("Luxury", "Luxury");
		return cabList;
		
	}
	
	
}
