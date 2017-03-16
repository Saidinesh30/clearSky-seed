package io.egen.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.entity.WeatherDetails;
import io.egen.api.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/weather")
@Api(tags = "Weather")
public class WeatherController {

	@Autowired
	private WeatherService service;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create weather readings", notes = "Creates city weather")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public WeatherDetails create(@RequestBody WeatherDetails weather) {
		return service.create(weather);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/cities")
	@ApiOperation(value = "Find All Cities", notes = "Returns a list of cities")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<String> findAllCities() {
		return service.findAllCities();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/latestweather/{city}")
	@ApiOperation(value = "Finds latest weather by city", notes = "Returns latest weather details of a city ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public WeatherDetails latestWeather(@PathVariable("city") String city) {
		return service.latestWeatherByCity(city);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/latestweather/{city}/{property}")
	@ApiOperation(value = "Find latest weather by city and property", notes = "Returns the value of the property")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String latestProperty(@PathVariable("city") String city, @PathVariable("property") String property) {
		return service.latestProperty(city, property);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hourlyavg/{city}/{date}/{hour}")
	@ApiOperation(value = "Find hourly averaged weather by city", notes = "Returns  average weather details for specified hour")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public WeatherDetails hourlyAvg(@PathVariable("city") String city, @PathVariable("date") String date, @PathVariable("hour") String hour) {
		return service.hourlyAvg(city,date,hour);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dailyavg/{city}/{date}")
	@ApiOperation(value = "Find daily averaged weather by city and date", notes = "Returns average weather details for specified day")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public WeatherDetails dailyAvg(@PathVariable("city") String city, @PathVariable("date") String date) {
		return service.dailyAvg(city, date);
	}

}
