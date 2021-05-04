package com.hingome.weatherapp.controller;

import java.net.URLEncoder;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.hingome.weatherapp.model.WeatherVO;
import com.hingome.weatherapp.model.WeatherVO.Weather;


@Controller
public class HomeController {

	/*
	 * @RequestMapping("/test") public String selectBasicSales(Model model) {
	 * 
	 * // Date date = new Date(); // SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyyMMdd", Locale.KOREA); // String today =
	 * sdf.format(date); // // Date dDate = new Date(); // dDate = new
	 * Date(dDate.getTime()+(1000*60*60*24*-7)); // SimpleDateFormat dSdf = new
	 * SimpleDateFormat("yyyyMMdd", Locale.KOREA); // String yesterday =
	 * dSdf.format(dDate);
	 * 
	 * SalesParamVO param = new SalesParamVO(); param.setStartDate("20200201");
	 * param.setEndDate("20200210");
	 * 
	 * List<BasicSalesVO> basicSalesList = homeService.selectBasicSales(param);
	 * model.addAttribute("basicSalesList", basicSalesList);
	 * 
	 * UserVO user = new UserVO(); user.setBaseCurrency("LAK"); List<ExchangeRateVO>
	 * exchangeRate = homeService.selectExchangeRate(user);
	 * model.addAttribute("exchangeRate", exchangeRate);
	 * 
	 * return "home"; }
	 */
	
	@GetMapping("about")
    @RequestMapping("about")
    public String about(Model model) throws Exception {

		
		
		return "/about";
	}
	
	
	private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
	private final String apiKey = "382337faac5360f4627378b171bb335c";  // 발급받은 API key
	@GetMapping("weather")
    @RequestMapping("weather")
	public String weatherApi(Model model,@RequestParam("description")Weather description,@RequestParam("name")String name) throws Exception {
		WeatherVO weatherVO = new WeatherVO();
		WeatherVO response = null;
		StringBuilder urlBuilder = new StringBuilder(BASE_URL);
		try {
			    urlBuilder.append("?" + URLEncoder.encode("lat", "UTF-8") + "=35");
			    urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=139");
			    urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
			    urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
			    urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");
	
			    RestTemplate restTemplate = new RestTemplate();
			    response = restTemplate.getForObject(urlBuilder.toString(), WeatherVO.class);
			 
			} 	catch (Exception e) {
		  		e.printStackTrace();
		  		
		  	}
			description = response.getWeather().get(2);
			name = response.getName();
					
			model.addAttribute(name,"name");
			model.addAttribute(description);
			return "/weather";
	}
}
