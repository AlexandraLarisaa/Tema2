package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ro.mta.se.lab.controller.ListController;
import ro.mta.se.lab.model.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main extends Application {

    private static Scene scene;
    private static String inFile = "in_file.txt";
    public static ArrayList<Location> locations = new ArrayList<Location>();
    public static Map<String, ArrayList<Location>> countryAndCity = new HashMap<String, ArrayList<Location>>();

    public void initializeCountries() throws FileNotFoundException {
        File inputFile = new File(inFile);
        Scanner scan = new Scanner(inputFile);
        String line_buffer;
        String[] buffer;
        int numberOfLocations = 0;

        while (scan.hasNextLine()) {

            line_buffer = scan.nextLine();
            buffer = line_buffer.split("\t");
            //ArrayList<String> cityList = new ArrayList<String>();
            //cityList.add(buffer[1]);
            Location location = new Location(buffer[4], buffer[1], buffer[2], buffer[3], buffer[0]);
           // locations.add(new Location(buffer[4], buffer[1], buffer[2], buffer[3], buffer[0]));

            if(!countryAndCity.containsKey((buffer[4]))){
                countryAndCity.put(buffer[4], new ArrayList<Location>());
            }

            countryAndCity.get(buffer[4]).add(location);
           // countryAndCity.put(buffer[4], cityList);

            numberOfLocations++;
            //System.out.println(l);
        }

        //System.out.println(countryAndCity.get("RU"));
        //System.out.println(countryAndCity.size());

    }

    @Override
    public void start(Stage stage) throws IOException {
        initializeCountries();

        scene = new Scene(loadFXML("primary"), 640, 480);

        stage.setScene(scene);
        stage.show();
    }

    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/view/" + fxml + ".fxml"));
        fxmlLoader.setController(new ListController(countryAndCity));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}