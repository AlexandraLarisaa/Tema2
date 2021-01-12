package ro.mta.se.lab.controller;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ro.mta.se.lab.model.Location;

import java.awt.image.BufferedImage;
import java.io.*;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ListController implements Initializable {

    private Map<String, ArrayList<Location>> locations;
    private String apiKey = "c50370646b59dcb6bb4dfa2622a8a900";

    private String currentCity;
    @FXML
    public ComboBox<String> chb_country;
    @FXML
    public ComboBox<String> chb_city;
    @FXML
    public Label showCity;
    @FXML
    public Label grade;
    @FXML
    public Label celsius;
    @FXML
    public Label time;
    @FXML
    public Label description;
    @FXML
    public ImageView weatherImage;
    @FXML
    public Label humidity;
    @FXML
    public Label wind;
    @FXML
    public Label precipitation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chb_country.setOnAction(this::handleSelectCountry);
        chb_city.setOnAction(this::handleSelectCity);
        chb_city.setDisable(true);
        celsius.setVisible(false);
        humidity.setVisible(false);
        wind.setVisible(false);
        precipitation.setVisible(false);

        for (String country : locations.keySet()) {
            chb_country.getItems().add(country);
        }
        chb_country.setValue("Select your country...");
        chb_city.setValue("Select your city...");

    }

    @FXML
    private void handleSelectCountry(ActionEvent actionEvent) {
        chb_city.getItems().clear();
        chb_city.setDisable(false);

        chb_city.setValue("Select your city...");
        String selectedCountry = chb_country.getValue();

        for (String country : locations.keySet()) {
            if (country.equals(selectedCountry)) {
                ArrayList<Location> city = locations.get(country);
                for (int i = 0; i < city.size(); i++) {
                    chb_city.getItems().add(city.get(i).getCity());
                }
            }
        }
        chb_city.setValue("Select your city...");
    }

    @FXML
    private void handleSelectCity(ActionEvent actionEvent) {
        showCity.setText(chb_city.getValue());

        try {

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + chb_city.getValue() + "&appid=" + apiKey);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String jsonBuffer = "";
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    jsonBuffer += scanner.nextLine();
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONObject json_obj = (JSONObject) parse.parse(jsonBuffer);
                JSONArray weather = (JSONArray) json_obj.get("weather");
                String icon = "";
                for (int i = 0; i < weather.size(); i++) {
                    JSONObject weather_i = (JSONObject) weather.get(i);
                    description.setText(weather_i.get("main").toString());
                    icon = weather_i.get("icon").toString();
                }
                System.out.println(icon);
                JSONObject main = (JSONObject) json_obj.get("main");
                DecimalFormat df = new DecimalFormat("0.0");

                float _grade = Float.parseFloat(main.get("temp").toString());
                _grade -= 273.15;

                System.out.println(df.format(_grade));
                grade.setText(String.valueOf(df.format(_grade)));
                celsius.setVisible(true);

                Long unixtime = (Long) json_obj.get("dt");
                Long timezone = (Long) json_obj.get("timezone");
                String type = "-";
                if (timezone > 0) {
                    type = "+";
                }
                int getTimeZone = 1;
                while (timezone != 0) {
                    getTimeZone++;
                    timezone /= 3600;
                }

                Date date = new Date(unixtime * 1000L);
                SimpleDateFormat jdf = new SimpleDateFormat("EEEE HH:mm aaa");

                jdf.setTimeZone(TimeZone.getTimeZone("GMT" + type + getTimeZone));
                String java_date = jdf.format(date);

                time.setText(java_date);

                URL url_img = new URL("http://openweathermap.org/img/wn/" + icon + "@2x.png");
                System.out.println("http://openweathermap.org/img/wn/" + icon + "@2x.png");

                InputStream in = new BufferedInputStream(url_img.openStream());
                OutputStream out = new BufferedOutputStream(new FileOutputStream(icon + ".png"));

                for ( int i; (i = in.read()) != -1; ) {
                    out.write(i);
                }
                in.close();
                out.close();

                Image img = new Image("file:" + icon + ".png");
                weatherImage.setImage(img);

                JSONObject clouds = (JSONObject) json_obj.get("clouds");
                JSONObject wind_obj = (JSONObject) json_obj.get("wind");
                System.out.println(clouds.get("all"));
                System.out.println(main.get("humidity"));

                humidity.setVisible(true);
                wind.setVisible(true);
                precipitation.setVisible(true);

                humidity.setText("Humidity: " + main.get("humidity") + "%");
                wind.setText("Wind: " + wind_obj.get("speed") + " km/h");
                precipitation.setText("Precipitation: " + clouds.get("all") + "%");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListController(Map<String, ArrayList<Location>> locations) {
        this.locations = locations;
    }


}
