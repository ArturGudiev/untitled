package utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.stream.Stream;

public class SimpleGermanTranslation {
    public static void main(String[] args) {

//        Document doc = BaBla.makeRequest(args[0]);
        String word = args[0];
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


        Element results = doc.getElementsByClass("sense-group-results").get(0);
        Elements answers = results.getElementsByTag("a");
        Object[] arr = Stream.of(answers).map(element -> element.text()).toArray();
        String answer=arr[0].toString().replaceAll(" ", ", ");
        System.out.println(answer);


    }
}
