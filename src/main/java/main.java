//Use a JSON array and have an 'id' on every object,
// then change your code so that the user can key an id into the terminal and details of the relevant object will be printed back to the user?
import java.io.IOException;
import java.text.ParseException;
public class main {

    public static void main(String[] args) throws IOException, ParseException {
        Info info = new Info("name.json");
        info.convertTomap();
        info.promptUser("Entering id....");
    }
}

