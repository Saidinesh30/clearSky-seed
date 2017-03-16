package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.WeatherDetails;

public interface WeatherService {

	WeatherDetails create(WeatherDetails weather);

	List<String> findAllCities();

	WeatherDetails latestWeatherByCity(String city);

	String latestProperty(String city, String property);

	WeatherDetails hourlyAvg(String city, String date, String hour);

	WeatherDetails dailyAvg(String city, String date);

}
