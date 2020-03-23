//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import java.io.File;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.HashMap;
//import java.util.Scanner;
//
//
//public class Info {
//    private String namesJsonFilePath;
//    private Scanner scanner = new Scanner(System.in);
//    private ObjectMapper mapper = new ObjectMapper();
//    private HashMap<Integer, Customer> namesMap = new HashMap<Integer, Customer>();
//
//
//
//    public Info(String namesJsonFilePath) throws IOException {
//        this.namesJsonFilePath = namesJsonFilePath;
//
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        Customer[] obj = mapper.readValue(new File(namesJsonFilePath), Customer[].class); //create array into object
//        for (int i = 0; i < obj.length; i++) { //loops through each array and sets each object to a hash map
//            namesMap.put(obj[i].getid(), obj[i]); //takes in key and a value, then links together
//            }
//        }
//
//
//    public int promptUser(String prompt) throws ParseException {
//        int idChoice = 0;
//        boolean isValid = false;
//        Customer selectedUser = null;
//        while (!isValid) {
//            System.out.print("\nPlease enter an ID: ");
//            idChoice = scanner.nextInt();
//
//            if (!namesMap.containsKey(idChoice)) {
//                System.out.print(idChoice + " is invalid");
//                continue;
//            }
//
//            selectedUser = namesMap.get(idChoice);
//            System.out.println("ID: " + selectedUser.getid());
//            System.out.println("Name: " + selectedUser.getName());
//            System.out.println("Booking confirmed?: " + selectedUser.getBooked());
//            System.out.println("Date Registered: " + selectedUser.getDateRegistered());
//            isValid = true;
//        }
//        return idChoice;
//    }
//}
//
//
