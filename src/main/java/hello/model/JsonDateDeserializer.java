//import org.springframework.format.annotation.DateTimeFormat;
//
//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//
//
//
//
////package hello.model;
////
////import com.fasterxml.jackson.core.JsonParser;
////import com.fasterxml.jackson.core.JsonToken;
////import com.fasterxml.jackson.databind.DeserializationContext;
////import com.fasterxml.jackson.databind.JsonDeserializer;
////
////import java.io.IOException;
////import java.text.ParseException;
////import java.text.SimpleDateFormat;
////import java.util.Date;
////
////    public class JsonDateDeserializer extends JsonDeserializer<Date> {
////
////        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
////
////        @Override
////        public Date deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
////            if (jsonParser.getCurrentToken().equals(JsonToken.VALUE_STRING)) {
////                try {
////                    Date date = format.parse(jsonParser.getText());
////                    return date;
////                } catch (ParseException e) {
////                }
////            }
////            return null;
////        }
////
////    }
////
////
////
////
////
////
