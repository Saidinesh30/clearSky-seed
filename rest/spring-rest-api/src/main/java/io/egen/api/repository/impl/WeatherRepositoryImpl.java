package io.egen.api.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.WeatherDetails;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.util.UtilServices;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public WeatherDetails create(WeatherDetails weather) {

		em.persist(weather);
		return weather;
	}

	@Override
	public List<String> findAllCities() {
		TypedQuery<String> query = em.createNamedQuery("WeatherDetails.findAllCities", String.class);
		return query.getResultList();
	}

	@Override
	public WeatherDetails latestWeatherByCity(String city) {
		TypedQuery<WeatherDetails> query = em.createNamedQuery("WeatherDetails.lastestWeather", WeatherDetails.class);
		query.setParameter("iCity", city);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<WeatherDetails> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public String latestProperty(String city, String property) {
		WeatherDetails weather = latestWeatherByCity(city);
		if (weather != null) {
			if (property.equalsIgnoreCase("temperature")) {
				return weather.getTemperature();
			} else if (property.equalsIgnoreCase("Humidity")) {
				return weather.getHumidity();
			} else if (property.equalsIgnoreCase("Pressure")) {
				return weather.getPressure();
			}
		}
		return "";
	}

	@Override
	public WeatherDetails hourlyAvg(String city) {
		return null;
	}

	@Override
	public WeatherDetails dailyAvg(String city, String date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = null;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

		TypedQuery<WeatherDetails> query = em.createNamedQuery("WeatherDetails.dailyweather", WeatherDetails.class);
		query.setParameter("iCity", city);
		query.setParameter("dailyDate", sqlDate);
		List<WeatherDetails> weatherList = query.getResultList();
		return UtilServices.WeatherUtil.getAvgWeather(weatherList, city, date);
	}


}
