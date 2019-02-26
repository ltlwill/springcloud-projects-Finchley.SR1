package com.efe.ms.serviceconsumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efe.ms.serviceconsumer.domain.Combo;
import com.efe.ms.serviceconsumer.domain.Product;
import com.efe.ms.serviceconsumer.service.ConsumerService;
import com.efe.ms.serviceconsumer.vo.Pagination;

@RefreshScope
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${commonConfig}")
	private String commonConfigValue;
	
	@Value("${configValue}")
	private String configValue;
	
	@Autowired
	private ConsumerService consumerService;
	
	@GetMapping
	public String getAppInfo(){
		return appName;
	}
	
	@GetMapping("/commonConfigValue")
	public String getCommonConfigValue(){
		return commonConfigValue;
	}
	
	@GetMapping("/configValue")
	public String getConfigValue(){
		return configValue;
	}
	
	@GetMapping("/products")
	public Pagination<Product> getProducts(Pagination<Product> page,Product product) throws Exception{
		Pagination<Product> pageData = consumerService.getProducts(page, product);
		return pageData;
	}
	
	@GetMapping("/products/{sku}")
	public Product getProductsBySku(@PathVariable String sku) throws Exception{
		return consumerService.getProductBySku(sku);
	}
	
	@GetMapping("/products/combo/{sku}")
	public List<Combo> getComboListBySku(@PathVariable String sku) throws Exception{
		return consumerService.getComboListBySku(sku); 
	}
}






