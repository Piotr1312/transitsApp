package transitsApp.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;

@Component
public class GoogleTransitInfoProvider implements TransitInfoProvider {

    private static final String API_KEY = "AIzaSyDxpv2-Ogdf9GF6eq3jrG7ciyphIFYHVy4";

    @Override
    public int getDistance(String source, String destination) {
        return getProperty(source, destination, Property.DISTANCE);
    }
    
    @Override
    public int getTime(String source, String destination) {
        return getProperty(source, destination, Property.DURATION);
    }
    
    private int getProperty(String source, String destination, Property property) {
        String jsonResponse;
        int value = 0;

        try {
            jsonResponse = getDataFromGoogleApi(source, destination);
            value = parseProperty(jsonResponse, property.getProperty());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    private String getDataFromGoogleApi(String source, String destination) throws URISyntaxException, IOException {
        String requestString = "https://maps.googleapis.com/maps/api/distancematrix/json?" + "origins=" + source
                + "&destinations=" + destination + "&key=" + API_KEY;
        URL requestUrl = new URL(requestString);
        URI uriFormatted = new URI("https", requestUrl.getHost(), requestUrl.getPath(), requestUrl.getQuery(), requestUrl.getRef());
        requestUrl = new URL(uriFormatted.toString());
        BufferedReader input = new BufferedReader(new InputStreamReader(requestUrl.openStream()));
        String temp = null;
        StringBuilder webSource = new StringBuilder();

        while ((temp = input.readLine()) != null)
            webSource.append(temp);

        input.close();

        return webSource.toString();
    }

    private int parseProperty(String jsonResponse, String property) throws JsonProcessingException, IOException {
        int value = 0;

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(jsonResponse);

        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();

                if (fieldName.equals(property)) {
                    for (int i = 0; i < 5; ++i)
                        parser.nextToken();

                    value = parser.getValueAsInt();
                }
            }
        }

        return value;
    }

    private static enum Property {
        DISTANCE("distance"), DURATION("duration");

        private String property;

        Property(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }

}
