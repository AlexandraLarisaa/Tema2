import org.junit.Before;
import org.junit.Test;
import ro.mta.se.lab.controller.ListController;
import ro.mta.se.lab.model.Location;
import ro.mta.se.lab.model.WeatherInfoClass;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListControllerTest {

    WeatherInfoClass weather;
    Location location;
    ListController listController;

    @Before
    public void beforeSetUp() {

        weather = mock(WeatherInfoClass.class);
        location = mock(Location.class);
        Map<String, ArrayList<Location>> locationsMap = new HashMap<String, ArrayList<Location>>();

        ArrayList<Location> location = new ArrayList<Location>();

        location.add(new Location("RO", "Bucuresti", "23.5", "34.2", "198630"));
        location.add(new Location("RO", "Bacau", "33.5", "14.2", "1423630"));
        location.add(new Location("RO", "Deva", "13.5", "44.2", "1238630"));

        locationsMap.put("RO", location);
        listController = new ListController(locationsMap);

    }

    @Test
    public void showWeatherInfo() {

        when(weather.getCityName()).thenReturn("Bacau");
        listController.getDescriptionInfo("Bacau","Soare","20%", "10%", "50km/h");

        try {
            Mockito.verify(weather).getCityName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}