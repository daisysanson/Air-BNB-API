//Use a JSON array and have an 'id' on every object,
// then change your code so that the user can key an id into the terminal and details of the relevant object will be printed back to the user?
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.corba.se.impl.protocol.InfoOnlyServantCacheLocalCRDImpl;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.naming.Name;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static javafx.scene.input.KeyCode.Z;

// 1587596400000

public class main {

    public static void main(String[] args) throws IOException, ParseException {

        Info info = new Info();
        info.convertTomap();
        info.promptUser("Entering id....");


//        HashMap<Integer, Names> namesMap = new HashMap<Integer, Names>();
//        Names[] obj = mapper.readValue(new File("name.json"), Names[].class);
//        Names n = null;
//        for (int i = 0; i<obj.length; i ++ ){ //loops through each array and sets each object to a key hash map
//            n = obj[i];
//            namesMap.put(obj[i].getid(), obj[i]); //put takes in key and value links togetehr
//        }
//        Names n1 = namesMap.get(2);
//        System.out.println(n1.getName());
//    }
//

    }
}

