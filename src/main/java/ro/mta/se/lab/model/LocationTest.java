package ro.mta.se.lab.model;

import org.junit.*;

public class LocationTest {
    private Location location;
    private Location location1;

    @Before
    public void init() {
        location = new Location("RO", "Bacau", "123", "123", "250");
        location1 = new Location("IT", "Roma", "456", "456", "260");

    }

    @Test
    public void getCountry() {
        Assert.assertEquals("RO", location.getCountry());
        Assert.assertEquals("IT", location1.getCountry());
    }

    @Test
    public void getCity() {
        Assert.assertEquals("Bacau", location.getCity());
        Assert.assertEquals("Roma", location1.getCity());

    }

    @Test
    public void getLatitude() {
        String latitude = location.getLatitude();
        if (!latitude.equals("123")) {
            Assert.fail();
        }
        String latitude1 = location1.getLatitude();
        if (!latitude1.equals("456")) {
            Assert.fail();
        }
    }

    @Test
    public void getLongitude() {
        String longitude = location.getLongitude();
        if (!longitude.equals("123")) {
            Assert.fail();
        }
        String longitude1 = location1.getLongitude();
        if (!longitude1.equals("456")) {
            Assert.fail();
        }
    }

    @Test
    public void getID() {
        Assert.assertEquals("250", location.getID());
        Assert.assertEquals("260", location1.getID());
    }
}