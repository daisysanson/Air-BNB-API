import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class Info {
    private String namesJsonFilePath;
    private Scanner scanner = new Scanner(System.in);
    private ObjectMapper mapper = new ObjectMapper();
    private HashMap<Integer, Names> namesMap = new HashMap<Integer, Names>(); //cr

    public Info(String namesJsonFilePath) {
        this.namesJsonFilePath = namesJsonFilePath;
    }

    public HashMap<Integer, Names> convertTomap() throws IOException, ParseException {
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Names[] obj = mapper.readValue(new File(namesJsonFilePath), Names[].class); //create array into object
        for (int i = 0; i < obj.length; i++) { //loops through each array and sets each object to a key hash map
            namesMap.put(obj[i].getid(), obj[i]); //put takes in key and value links togetehr
        }
        return namesMap;
    }


    public int promptUser(String prompt) throws ParseException {
        int idChoice = 0;
        boolean isValid = false;
        Names selectedUser = null;
        while (!isValid) {
            System.out.print("\nPlease enter an ID: ");
            idChoice = scanner.nextInt();

            if (!namesMap.containsKey(idChoice)) {
                System.out.print(idChoice + " is invalid");
                continue;
            }

            selectedUser = namesMap.get(idChoice);
            System.out.println("ID: " + selectedUser.getid());
            System.out.println("Name: " + selectedUser.getName());
            System.out.println("Booking confirmed?: " + selectedUser.getBooked());
            System.out.println("Date Registered: " + selectedUser.getDateRegistered());
            isValid = true;
        }
        return idChoice;
    }
}


