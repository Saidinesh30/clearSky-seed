package io.egen.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.WeatherDetails;
import io.egen.api.exception.BadRequestException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Autowired
	private WeatherRepository weatherRepo;
	
	List<String> properties= new ArrayList<>(Arrays.asList("temperature","humidity","pressure"));
	
	@Override
	@Transactional
	public WeatherDetails create(WeatherDetails weather) {
		return weatherRepo.create(weather);
	}

	@Override
	public List<String> findAllCities() {
		return weatherRepo.findAllCities();
	}

	@Override
	public WeatherDetails latestWeatherByCity(String city) {
		
		WeatherDetails weather=weatherRepo.latestWeatherByCity(city);
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
		String wProperty=weatherRepo.latestProperty(city,property);
		if(wProperty.isEmpty()){
			throw new BadRequestException("City  " + city + " does not exist");
		}
		else{
			return wProperty;
		}
	}

	@Override
	public WeatherDetails hourlyAvg(String city) {
		
		return weatherRepo.hourlyAvg(city);
	}

	@Override
	public WeatherDetails dailyAvg(String city,String date) {
		
		WeatherDetails avgWeather= weatherRepo.dailyAvg(city,date);
		if(avgWeather==null){
			throw new BadRequestException("City  " + city + " does not exist");
		}
		else{
			return avgWeather;
		}
	}

}
