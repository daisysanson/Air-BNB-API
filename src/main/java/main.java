import java.io.IOException;
import java.text.ParseException;
public class main {

    public static void main(String[] args) throws IOException, ParseException {
        Info info = new Info("name.json");
        info.promptUser("Entering id....");
    }
}

