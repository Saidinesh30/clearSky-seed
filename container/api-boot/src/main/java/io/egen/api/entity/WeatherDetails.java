package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WeatherDetails{

	@Id

	private String weatherId;
	private String city;
	private String description;
	private String humidity;
	private String pressure;
	private String temperature;

	@OneToOne(cascade = { CascadeType.ALL })
	private WindDetails wind;

	private String timestamp;

	public WeatherDetails() {
		this.weatherId = UUID.randomUUID().toString();
	}

	@JsonIgnore
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {

		this.timestamp=timestamp;
	}

	@Override
	public String toString() {
		return "WeatherDetails [id=" + weatherId + ", city=" + city + ", description=" + description + ", humidity=" + humidity
				+ ", pressure=" + pressure + ", temperature=" + temperature + ", wind=" + wind + ", timestamp="
				+ timestamp + "]";
	}

}
