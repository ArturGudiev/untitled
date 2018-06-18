package utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class BaBla {
    static Document makeRequest(String word) {
        String content = null;
        word = word.replaceAll("ä", "ae");
        word = word.replaceAll("ö", "oe");
        word = word.replaceAll("ü", "ue");
        URLConnection connection = null;
        try {
            connection =  new URL("https://en.bab.la/dictionary/german-russian/"+word).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        Document doc = Jsoup.parse(content);

        return doc;
    }
}
