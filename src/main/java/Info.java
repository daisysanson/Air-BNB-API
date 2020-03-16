import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class Info {
    Names names;
    JSONArray name;
    Scanner scanner = new Scanner(System.in);
    ObjectMapper mapper = new ObjectMapper();
    int idChoice;
    boolean isValid = false;
    HashMap<Integer, Names> namesMap = new HashMap<Integer, Names>();

    public Info(Names names, JSONArray name, Scanner scanner, ObjectMapper mapper, int idChoice, boolean isValid, HashMap<Integer, Names> namesMap) {
        this.names = names;
        this.name = name;
        this.scanner = scanner;
        this.mapper = mapper;
        this.idChoice = idChoice;
        this.isValid = isValid;
        this.namesMap = namesMap;
    }

    public Info() {

    }

    public void formatDate() throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formatted = simpleDateFormat.format(new Date());
        Date date = simpleDateFormat.parse(formatted); //had to call date in differnt varibale as wouldn't work directly in constructer
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public HashMap<Integer, Names> convertTomap() throws IOException, ParseException {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Names[] obj = mapper.readValue(new File("name.json"), Names[].class);
        Names n = null;
        for (int i = 0; i < obj.length; i++) { //loops through each array and sets each object to a key hash map
            n = obj[i];
            namesMap.put(obj[i].getid(), obj[i]); //put takes in key and value links togetehr
        }

        return namesMap;
    }



    public int promptUser(String prompt) throws ParseException {
        while (!isValid) {
            System.out.print("\nPlease enter an ID: ");
            int idChoice = scanner.nextInt();
            if (namesMap.containsKey(idChoice)) {
                Names n1 = namesMap.get(idChoice);
                System.out.println("Name: "+ n1.getName());
                System.out.println("Booking confirmed?: "+ n1.getBooked());
                System.out.println("Date Registerd: " + n1.getDateRegistered());
                break;
            } else {
                System.out.print(idChoice + " is invalid");
                continue;

            }
        }
        return idChoice;
    }
}
