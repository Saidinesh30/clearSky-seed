package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.WeatherDetails;

public interface WeatherRepository {

	WeatherDetails create(WeatherDetails weather);

	List<String> findAllCities();

	WeatherDetails latestWeatherByCity(String city);

	String latestProperty(String city, String property);

	WeatherDetails hourlyAvg(String city);

	WeatherDetails dailyAvg(String city, String date);


}
