package ro.mta.se.lab.model;

import org.junit.*;

public class LocationTest {
    private Location location;

    @Before
    public void init(){
        location = new Location("RO","Bacau","123","123","250");
    }
    @Test
    public void getCountry() {
        Assert.assertEquals("RO", location.getCountry());
    }

    @Test
    public void getCity() {
        Assert.assertEquals("Bacau", location.getCity());
    }

    @Test
    public void getLatitude() {
        String latitude = location.getLatitude();
        if(!latitude.equals("123")){
            Assert.fail();
        }
    }

    @Test
    public void getLongitude() {
        String longitude = location.getLongitude();
        if(!longitude.equals("123")){
            Assert.fail();
        }
    }

    @Test
    public void getID() {
        Assert.assertEquals("250", location.getID());
    }
}