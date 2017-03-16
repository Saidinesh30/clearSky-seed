package io.egen.api.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import io.egen.api.entity.WeatherDetails;

public interface WeatherRepository extends Repository<WeatherDetails, String>{

	WeatherDetails save(WeatherDetails weather);

	@Query("SELECT distinct u.city FROM WeatherDetails u ORDER BY u.city")
	List<String> findAllCities();

	WeatherDetails findTopByCityOrderByTimestampDesc(String city);

	@Query("SELECT u from WeatherDetails u where u.city=:city and timestamp like concat(:date,'%')")
	List<WeatherDetails> hourlyAvg(@Param("city") String city, @Param("date") String date);

	@Query("SELECT u from WeatherDetails u where u.city=:city and date(u.timestamp)=:date")
	List<WeatherDetails> dailyAvg(@Param("city") String city, @Param("date") Date sqlDate);


}
