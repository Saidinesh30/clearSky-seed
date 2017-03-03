package io.egen.api.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "WeatherDetails.findAllCities", query = "SELECT distinct u.city FROM WeatherDetails u ORDER BY u.city"),
		@NamedQuery(name="WeatherDetails.lastestWeather",query="SELECT u FROM WeatherDetails u where u.city=:iCity ORDER BY u.timestamp DESC"),
		@NamedQuery(name="WeatherDetails.dailyweather",query="SELECT u from WeatherDetails u where u.city=:iCity and date(u.timestamp)=:dailyDate")
})
public class WeatherDetails {

	@Id
	private String weatherId;
	private String city;
	private String description;
	private String humidity;
	private String pressure;
	private String temperature;

	@OneToOne(cascade = { CascadeType.ALL })
	private WindDetails wind;

	// @Temporal(TemporalType.TIMESTAMP)
	
	private Date timestamp;

	public WeatherDetails() {
		this.weatherId = UUID.randomUUID().toString();
	}

	public String getId() {
		return weatherId;
	}

	public void setId(String id) {
		this.weatherId = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public WindDetails getWind() {
		return wind;
	}

	public void setWind(WindDetails wind) {

		this.wind = wind;

	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		try {
			this.timestamp = date.parse(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "WeatherDetails [id=" + weatherId + ", city=" + city + ", description=" + description + ", humidity=" + humidity
				+ ", pressure=" + pressure + ", temperature=" + temperature + ", wind=" + wind + ", timestamp="
				+ timestamp + "]";
	}

}
