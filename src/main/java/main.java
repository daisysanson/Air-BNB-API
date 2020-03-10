
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

// 1587596400000

public class main {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Names[] name = mapper.readValue(new File("name.json"), Names[].class); ///this turning into string into an array //otherwise error trown expercting object
        String customer = mapper.writeValueAsString(name);
        System.out.println(customer);










//
//        Car car = new Car("yellow", "renault", 4);  //creating a java ob to json string
//        mapper.writeValue(new File("car.json"), car);
//        String carAsString = mapper.writeValueAsString(car);
//        System.out.println(carAsString);
//
//        // mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); //converting *from* snake case
//        String json = "{ \"colour\" : \"Black\", \"type\" : \"BMW\", \"no_of_wheels\" : 4} "; //converting json into java object converting field into json needs a field to recognise it
//        // Deserialize the Json into a car object
//        Car car1 = mapper.readValue(json, Car.class);
//        carAsString = mapper.writeValueAsString(car1);
//        System.out.println(carAsString);
//        System.out.println(car1.getColour());
//        System.out.println(car1.getDate()); //goes to getters to read
//





    }
}