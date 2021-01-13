package ro.mta.se.lab.model;

/**
 * @author Orăș Alexandra
 *
 * Această clasă reține informațiile meteo.
 */
public class WeatherInfoClass {

    private String cityName;
    private String description;
    private String humidity;
    private String precipitation;
    private String wind;

    public WeatherInfoClass(String cityName, String description, String humidity, String precipitation, String wind) {
        this.cityName = cityName;
        this.description = description;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.wind = wind;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getWind() {
        return wind;
    }
}
