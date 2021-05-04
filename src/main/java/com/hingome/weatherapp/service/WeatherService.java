package com.hingome.weatherapp.service;

import java.net.URLEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hingome.weatherapp.model.WeatherVO;



@Service

public class WeatherService {
 public void weatherService() {
	String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
	String apiKey = "382337faac5360f4627378b171bb335c";  // 발급받은 API key
		WeatherVO weatherVO = new WeatherVO();
	
		StringBuilder urlBuilder = new StringBuilder(BASE_URL);
		try {
		    urlBuilder.append("?" + URLEncoder.encode("lat", "UTF-8") + "=35");
		    urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=139");
		    urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
		    urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
		    urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

		    RestTemplate restTemplate = new RestTemplate();
		    WeatherVO response = restTemplate.getForObject(urlBuilder.toString(), WeatherVO.class);
		    
	  	}catch(Exception e){
	  	 e.printStackTrace();
	  	}
	}
}
