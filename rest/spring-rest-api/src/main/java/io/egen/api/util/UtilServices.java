package io.egen.api.util;

import java.util.List;

import org.springframework.stereotype.Component;

import io.egen.api.entity.WeatherDetails;
import io.egen.api.entity.WindDetails;

@Component
public class UtilServices {

	public static class WeatherUtil {

		public static WeatherDetails getAvgWeather(List<WeatherDetails> weatherList, String city, String date) {
			WeatherDetails avgWeather = null;
			if (!weatherList.isEmpty()) {
				float avgTemp = 0, avgHum = 0, avgPres = 0, avgDeg = 0, avgSpeed = 0;
				float total = weatherList.size();
				avgWeather = new WeatherDetails();
				WindDetails wind = new WindDetails();
				for (WeatherDetails weather : weatherList) {
					avgTemp = avgTemp + Float.parseFloat(weather.getTemperature());
					avgHum = avgHum + Float.parseFloat(weather.getHumidity());
					avgPres = avgPres + Float.parseFloat(weather.getPressure());
					avgDeg = avgDeg + Float.parseFloat(weather.getWind().getDegree());
					avgSpeed = avgSpeed + Float.parseFloat(weather.getWind().getSpeed());
				}
				avgWeather.setCity(city);
				avgWeather.setTimestamp(weatherList.get(0).getTimestamp().toString());
				avgWeather.setDescription("");
				avgWeather.setTemperature(String.format("%.2f", (avgTemp / total)));
				avgWeather.setHumidity(String.format("%.2f", (avgHum / total)));
				avgWeather.setPressure(String.format("%.2f", (avgPres / total)));
				wind.setDegree(String.format("%.2f", (avgDeg / total)));
				wind.setSpeed(String.format("%.2f", (avgSpeed / total)));
				avgWeather.setWind(wind);
			}
			return avgWeather;
		}
	}
}
