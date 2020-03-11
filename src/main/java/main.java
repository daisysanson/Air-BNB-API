
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javafx.scene.input.KeyCode.Z;

// 1587596400000

public class main {

    public static void main(String[] args) throws IOException, ParseException {

        ObjectMapper mapper = new ObjectMapper();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formatted = simpleDateFormat.format(new Date());
        Date date = simpleDateFormat.parse(formatted); //had to call date in differnt varibale as wouldn't work directly in constructer
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        //converts json array to Names object
        Names[] name = mapper.readValue(new File("name.json"), Names[].class);
        String customer = mapper.writeValueAsString(name);///this turns into string into an array //otherwise error appears expercting object
        String customer2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(name); //makes it look nicer when printing
        System.out.println(customer);
        System.out.println(customer2);


    Names newEntry = new Names(4,"Santa", date, false); //will print todays date
    mapper.writeValue(new File("newEntry.json"), newEntry);
    String newCustomerString = mapper.writeValueAsString(newEntry);
    System.out.print(newCustomerString); //using java class Names as a base to turn a new customer's information

        








    }
}