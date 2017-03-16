package io.egen.api.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.WeatherDetails;
import io.egen.api.exception.BadRequestException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.service.WeatherService;
import io.egen.api.util.UtilServices;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	
	private WeatherRepository weatherRepo;
	
	public WeatherServiceImpl(WeatherRepository weatherRepo) {
		this.weatherRepo = weatherRepo;
	}
	
	List<String> properties= new ArrayList<>(Arrays.asList("temperature","humidity","pressure"));
	
	@Override
	@Transactional
	public WeatherDetails create(WeatherDetails weather) {
		return weatherRepo.save(weather);
	}

	@Override
	
	public List<String> findAllCities() {
		return weatherRepo.findAllCities();
	}

	@Override
	public WeatherDetails latestWeatherByCity(String city) {
		
		WeatherDetails weather=weatherRepo.findTopByCityOrderByTimestampDesc(city);
		if(weather==null){
			throw new BadRequestException("City  " + city + " does not exist");
		}
		else{
			return weather;
		}
	}

	@Override
	public String latestProperty(String city,String property) {
		
		if(!property.contains(property.toLowerCase())){
			throw new BadRequestException("Property   " + city + " not found");
		}
		WeatherDetails weather=weatherRepo.findTopByCityOrderByTimestampDesc(city);
		if (weather == null) {
			throw new BadRequestException("City  " + city + " does not exist");
			
		}
		else{
			if (property.equalsIgnoreCase("temperature")) {
				return weather.getTemperature();
			} else if (property.equalsIgnoreCase("Humidity")) {
				return weather.getHumidity();
			} else if (property.equalsIgnoreCase("Pressure")) {
				return weather.getPressure();
			}
		}
		return property;
		
	}

	@Override
	public WeatherDetails hourlyAvg(String city,String date,String hour) {
		
		List<WeatherDetails> weatherList= weatherRepo.hourlyAvg(city, date+"T"+hour);
		
		if(weatherList.isEmpty()){
			throw new BadRequestException("Given parameter do not have any data");
		}
		
		return  UtilServices.WeatherUtil.getAvgWeather(weatherList, city, date);
		
		
	}

	@Override
	public WeatherDetails dailyAvg(String city,String date) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = null;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			throw new BadRequestException("Date format is not correct.Enter in yyyy-MM-dd format");
		}
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

			
		List<WeatherDetails> weatherList= weatherRepo.dailyAvg(city,sqlDate);
		if(weatherList==null){
			throw new BadRequestException("Given parameter do not have any data");
		}
		else{
			return UtilServices.WeatherUtil.getAvgWeather(weatherList, city, date);
		}
	}

}
