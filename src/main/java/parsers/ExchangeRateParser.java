package parsers;

import com.google.gson.Gson;
import entity.Currency;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeRateParser {

    public static Currency[] parse(String link) {
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(reader, Currency[].class);
    }
}
